package lite.crud.application.util.opc.value.date;

import lite.crud.application.util.opc.value.ValueOpc;

/**
 * @author xl-9527
 * @since 2024/8/24
 */
public abstract class DateValueOpc<T> implements ValueOpc<T, String, String> {

	public String toCommonDate(T val) {
		return this.format(val, "yyyy-MM-dd HH:mm:ss");
	}
}
