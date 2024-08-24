package lite.crud.application.util.opc.value.number;

import lite.crud.application.util.opc.value.ValueOpc;

import java.math.BigDecimal;

/**
 * @author xl-9527
 * @since 2024/8/24
 */
public abstract class NumValueOpc<T> implements ValueOpc<T, Integer, T> {

	public abstract BigDecimal toBigDecimal(T val);
}
