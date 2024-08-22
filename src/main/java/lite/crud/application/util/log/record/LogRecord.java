package lite.crud.application.util.log.record;


import lite.crud.application.util.log.record.bean.LogRecordBean;

import java.util.List;
import java.util.Map;

/**
 * @author xl-9527
 * @since 2024/8/22
 */
public interface LogRecord {

	<T extends LogRecordBean> List<T> parseLogs(Object newObject, final Object oldObject, Class<T> clazz, Map<String, String> fieldMap);
}
