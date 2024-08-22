package lite.crud.application.util.log;

import lite.crud.application.util.log.record.LogRecord;
import lite.crud.application.util.log.record.RecordSupport;
import lite.crud.application.util.log.record.bean.LogRecordBean;
import lite.crud.application.util.log.record.bean.LogRecordFieldMap;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 日志记录工具
 *
 * @author xl-9527
 * @since 2024/8/22
 */
public class LogRecordUtil {

	private static final LogRecord LOG_RECORD = RecordSupport.INSTANCE;


	/**
	 * 解析日志
	 */
	public static <T extends LogRecordBean> List<T> getLogRecord(final Object newObject, final Object oldObject, final Class<T> clazz, final Map<String, String> fieldMap) {
		return LOG_RECORD.parseLogs(newObject, oldObject, clazz, fieldMap);
	}

	/**
	 * 解析日志
	 */
	public static <T extends LogRecordBean, O extends LogRecordFieldMap> List<T> getLogRecord(final O newObject, final O oldObject, final Class<T> clazz) {
		return LOG_RECORD.parseLogs(newObject, oldObject, clazz, newObject.allFieldMap());
	}

	/**
	 * 构建日志集合
	 */
	public static <T extends LogRecordBean> Builder<T> builder(final Object newObject, final Object oldObject, final Class<T> clazz, final Map<String, String> fieldMap) {
		return new Builder<>(getLogRecord(newObject, oldObject, clazz, fieldMap));
	}

	/**
	 * 构建日志集合
	 */
	public static <T extends LogRecordBean, O extends LogRecordFieldMap> Builder<T> builder(final O newObject, final O oldObject, final Class<T> clazz) {
		return new Builder<>(getLogRecord(newObject, oldObject, clazz));
	}

	public static class Builder<T extends LogRecordBean> {

		private final List<T> recordList;

		public Builder(final List<T> recordList) {
			this.recordList = Optional.ofNullable(recordList).orElse(List.of());
		}

		public Builder<T> dataId(final Object dataId) {
			recordList.forEach(v -> v.setDataId(String.valueOf(dataId)));
			return this;
		}

		public List<T> build() {
			return recordList;
		}
	}
}
