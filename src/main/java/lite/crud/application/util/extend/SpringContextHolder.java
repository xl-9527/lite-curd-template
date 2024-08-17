package lite.crud.application.util.extend;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.Ordered;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * @author xl-9527
 * @since 2024/8/17
 */
@Component
public class SpringContextHolder implements ApplicationContextAware, Ordered {

	private static ApplicationContext applicationContext;

	public static <T> T getBean(final Class<T> clazz) {
		return applicationContext.getBean(clazz);
	}

	@Override
	public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
		SpringContextHolder.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	@Override
	public int getOrder() {
		return -1;
	}
}
