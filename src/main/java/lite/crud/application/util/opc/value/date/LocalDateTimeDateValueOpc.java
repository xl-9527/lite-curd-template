package lite.crud.application.util.opc.value.date;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * @author xl-9527
 * @since 2024/8/24
 */
public class LocalDateTimeDateValueOpc extends DateValueOpc<LocalDateTime> {

	@Override
	public String format(final LocalDateTime val, final String format) {
		if (Objects.isNull(val) || Objects.isNull(format)) {
			return StringUtils.EMPTY;
		}

		return val.format(DateTimeFormatter.ofPattern(format));
	}
}
