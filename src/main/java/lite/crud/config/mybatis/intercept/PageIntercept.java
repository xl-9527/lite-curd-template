package lite.crud.config.mybatis.intercept;

import lite.crud.config.common.pojo.Page;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author xl-9527
 * @since 2024/9/6
 */
@Intercepts(
        @Signature(type = StatementHandler.class, method = "prepare", args = {java.sql.Connection.class, Integer.class})
)
@Component
public class PageIntercept implements Interceptor {

    private final Logger log = LoggerFactory.getLogger(PageIntercept.class);

    @Override
    public Object intercept(final Invocation invocation) throws Throwable {
        this.judgementPage(invocation);
        return invocation.proceed();
    }

    /**
     * Determine whether pagination is possible
     */
    private void judgementPage(final Invocation invocation) throws SQLException {
        final Object target = invocation.getTarget();
        if (target instanceof StatementHandler statementHandler) {
            final BoundSql boundSql = statementHandler.getBoundSql();
            final ParameterHandler parameterHandler = statementHandler.getParameterHandler();
            final Object parameterObject = parameterHandler.getParameterObject();
            if (ObjectUtils.isNotEmpty(parameterObject) && parameterObject instanceof Map<?,?> pMap) {
                for (final Object value : pMap.values()) {
                    if (ObjectUtils.isNotEmpty(value) && value instanceof Page<?> page && !boundSql.getSql().contains("LIMIT")) {
                        final Object[] args = invocation.getArgs();
                        if (ObjectUtils.isNotEmpty(args) && args[0] instanceof Connection connection) {
                            // handler page
                            this.resetBoundSql(boundSql, page);
                            this.handlerPage(connection, page, boundSql, parameterHandler);
                        }

                        log.warn("current connection is null -> {}", page);
                    }
                }
            }
        }
    }

    /**
     * reset sql to have limit
     */
    private void resetBoundSql(final BoundSql boundSql, final Page<?> page) {
        final MetaObject metaObject = SystemMetaObject.forObject(boundSql);
        final Integer current = page.getCurrent();
        final String newSql = String.format("%s LIMIT %d, %d", boundSql.getSql(), ((current - 1) * page.getSize()), page.getSize());
        metaObject.setValue("sql", newSql);
    }

    private void handlerPage(final Connection connection, final Page<?> page, final BoundSql boundSql, final ParameterHandler parameterHandler) throws SQLException {
        final String sql = boundSql.getSql();
        final PreparedStatement prepareStatement = connection.prepareStatement(
                String.format("SELECT COUNT(1) FROM (%s) TOTAL;", sql)
        );
        parameterHandler.setParameters(prepareStatement);
        final ResultSet resultSet = prepareStatement.executeQuery();
        if (resultSet.next()) {
            page.setTotal(resultSet.getInt(1));
        } else {
            page.setTotal(0);
        }
    }
}
