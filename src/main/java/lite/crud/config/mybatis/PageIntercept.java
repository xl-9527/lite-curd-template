package lite.crud.config.mybatis;

import lite.crud.config.common.pojo.Page;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author xl-9527
 * @since 2024/9/6
 */
@Intercepts(
        @Signature(type = StatementHandler.class, method = "prepare", args = {java.sql.Connection.class, Integer.class})
)
@Component
public class PageIntercept implements Interceptor {

    @Override
    public Object intercept(final Invocation invocation) throws Throwable {
        final Method method = invocation.getMethod();
        for (final Class<?> parameterType : method.getParameterTypes()) {
            if (parameterType.isAssignableFrom(Page.class)) {
                // invoke page query
            }
        }
        final Object proceed = invocation.proceed();
        return proceed;
    }
}
