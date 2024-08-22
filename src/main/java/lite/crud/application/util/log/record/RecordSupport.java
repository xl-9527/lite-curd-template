package lite.crud.application.util.log.record;


import lite.crud.application.util.log.record.bean.LogRecordBean;
import lite.crud.application.util.opc.json.JSONOpcUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author xl-9527
 * @since 2024/8/22
 */
public class RecordSupport implements LogRecord {

	private final Logger log = LoggerFactory.getLogger(RecordSupport.class);

	private final boolean enableFullRecording;

	private RecordSupport(boolean enableFullRecording) {
		this.enableFullRecording = enableFullRecording;
	}

	public static final LogRecord INSTANCE = new RecordSupport(true);


	@Override
	public <T extends LogRecordBean> List<T> parseLogs(final Object newObject, final Object oldObject, final Class<T> clazz, final Map<String, String> fieldMap) {
		if (enableFullRecording && ObjectUtils.isEmpty(fieldMap)) {
			log.warn("日志记录开启配置字段模式，字段映射不存在默认不做解析");
			return List.of();
		}
		final Map<String, Object> newObjsMap = this.getAllField(newObject, newObject.getClass());
		final Map<String, Object> oldObjsMap = this.getAllField(oldObject, oldObject.getClass());
		final Map<String, Object[]> diff = this.compareAndGetDiff(newObjsMap, oldObjsMap);
		return this.translateToList(diff, fieldMap, clazz);
	}

	/**
	 * 获取 filed & value
	 */
	private Map<String, Object> getAllField(final Object obj, final Class<?> clazz) {
		final Map<String, Object> result = new HashMap<>();

		for (final Field declaredField : clazz.getDeclaredFields()) {
			declaredField.setAccessible(true);
			try {
				result.put(declaredField.getName(), declaredField.get(obj));
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}

		return result;
	}

	/**
	 * 对比，并且获取对比结果
	 *
	 * @param newObjsMap 最新的数据
	 * @param oldObjsMap 老数据
	 * @return 对比结果 => field：[before，after]
	 */
	private Map<String, Object[]> compareAndGetDiff(final Map<String, Object> newObjsMap, final Map<String, Object> oldObjsMap) {
		if (newObjsMap == null || oldObjsMap == null) {
			return Map.of();
		}

		/* field：[before，after] */
		final Map<String, Object[]> result = new HashMap<>();
		for (final Map.Entry<String, Object> entry : newObjsMap.entrySet()) {
			final String key = entry.getKey();

			if (oldObjsMap.containsKey(key) && !Objects.equals(oldObjsMap.get(key), entry.getValue())) {
				result.put(key, new Object[]{oldObjsMap.get(key), entry.getValue()});
			}
		}

		return result;
	}

	private <T extends LogRecordBean> List<T> translateToList(final Map<String, Object[]> diff, final Map<String, String> fieldMap, Class<T> clazz) {
		if (diff != null) {
			return diff.entrySet().stream().flatMap(entry -> {
				final String key = entry.getKey();
				final String fieldName = Optional.ofNullable(fieldMap).orElse(Map.of()).get(key);
				if (fieldName == null && enableFullRecording) {
					log.debug("字段 -> {}, 被过滤，因为没有匹配到对应的字段信息，filedMap 配置为 -> {}", key, JSONOpcUtil.DEFAULT.toJSONStr(fieldMap));
					return Stream.of();
				}
				final Object[] value = entry.getValue();
				final Date now = new Date();
				try {
					final T t = clazz.getDeclaredConstructor().newInstance();
					t.setCreateAt(now);
					t.setField(key);
					t.setFieldName(fieldName);
					t.setBeforeValue(value[0]);
					t.setAfterValue(value[1]);
					try {
						t.setUserName(null);
						throw new RuntimeException("");
					} catch (Exception e) {
						log.error("获取用户名异常 -> {}", e.getMessage());
					}
					return Stream.of(t);
				} catch (InstantiationException | IllegalAccessException |
						 NoSuchMethodException | InvocationTargetException e) {
					throw new RuntimeException(e);
				}
			}).collect(Collectors.toList());
		}
		return List.of();
	}
}
