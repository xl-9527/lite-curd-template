package lite.crud.config.mybatis;

import org.apache.ibatis.executor.result.DefaultResultHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author xl-9527
 * @since 2024/9/8
 **/
public class PageResultHandler extends DefaultResultHandler {

    @Override
    public List<Object> getResultList() {

        final List<Object> resultList = super.getResultList();
        Optional.ofNullable(resultList).orElse(new ArrayList<>());
        return super.getResultList();
    }
}
