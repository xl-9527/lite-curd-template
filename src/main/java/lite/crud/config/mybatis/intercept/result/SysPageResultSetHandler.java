package lite.crud.config.mybatis.intercept.result;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.executor.resultset.ResultSetHandler;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @author xl-9527
 * @since 2024/9/8
 **/
public class SysPageResultSetHandler implements ResultSetHandler {

    @Override
    public <E> List<E> handleResultSets(final Statement stmt) throws SQLException {
        return List.of();
    }

    @Override
    public <E> Cursor<E> handleCursorResultSets(final Statement stmt) throws SQLException {
        return null;
    }

    @Override
    public void handleOutputParameters(final CallableStatement cs) throws SQLException {

    }
}
