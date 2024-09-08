package lite.crud.config.mybatis.intercept;

import lite.crud.config.common.pojo.Page;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import java.sql.Statement;
import java.util.List;

/**
 * @author xl-9527
 * @since 2024/9/8
 **/
@Intercepts({
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class})
})
@Component
public class PageResultIntercept implements Interceptor {

    @Override
    public Object intercept(final Invocation invocation) throws Throwable {
        if (invocation.getTarget() instanceof ResultSetHandler resultSetHandler) {
            System.out.println(resultSetHandler.getClass().getName());
            return invocation.proceed();
        }

        final Object[] args = invocation.getArgs();
        final Object proceed = invocation.proceed();

        for (final Object arg : args) {
            // page result handler
            if (arg instanceof MapperMethod.ParamMap<?> paramMap) {
                for (final Object val : paramMap.values()) {
                    if (val instanceof Page<?> page && proceed instanceof List<?> records) {
                        SystemMetaObject.forObject(page).setValue("record", records);
                    }
                }
            }
        }

        return proceed;
    }
}
