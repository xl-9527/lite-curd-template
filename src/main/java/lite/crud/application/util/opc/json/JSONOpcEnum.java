package lite.crud.application.util.opc.json;

import org.springframework.context.ApplicationContext;
import org.springframework.lang.NonNull;

/**
 * @author xl-9527
 * @since 2024/8/16
 */
public enum JSONOpcEnum {
	FAST_JSON {
		@Override
		JSONOpc getJSONOpc(@NonNull final ApplicationContext applicationContext) {
			throw new NullPointerException("wait coding");
		}
	},
	FAST_JSON2 {
		@Override
		JSONOpc getJSONOpc(@NonNull final ApplicationContext applicationContext) {
			throw new NullPointerException("wait coding");
		}
	},
	JACKSON {
		@Override
		JSONOpc getJSONOpc(@NonNull final ApplicationContext applicationContext) {
			return applicationContext.getBean(JSONOpcJackson.class);
		}
	};

	abstract JSONOpc getJSONOpc(@NonNull ApplicationContext applicationContext);
}
