package lite.crud.application.util.opc.value.number;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * @author xl-9527
 * @since 2024/8/24
 */
public class DoubleNumValueOpc extends NumValueOpc<Double> {

	@Override
	public BigDecimal toBigDecimal(final Double val) {
		if (Objects.isNull(val)) {
			return null;
		}
		return BigDecimal.valueOf(val);
	}

	@Override
	public Double format(final Double val, final Integer integer) {
		if (Objects.isNull(val) || Objects.isNull(integer)) {
			return val;
		}

		return this.toBigDecimal(val).setScale(integer, RoundingMode.HALF_UP).doubleValue();
	}
}
