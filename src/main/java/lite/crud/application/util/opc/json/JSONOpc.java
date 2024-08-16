package lite.crud.application.util.opc.json;

import java.util.List;

/**
 * @author xl-9527
 * @since 2024/8/16
 */
public interface JSONOpc {

	String toJSONStr(final Object obj);

	<T> T parseObject(final String jsonStr, final Class<T> clazz);

	<T> List<T> parseArray(final String jsonStr, final Class<T> clazz);

	JSONOpcEnum supportLib();
}
