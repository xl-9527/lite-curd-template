package lite.crud.application.base;

import lite.crud.application.BizEventService;
import lite.crud.application.util.dto.PageParams;

import java.util.List;

/**
 * @author xl-9527
 * @since 2024/8/2
 */
public interface BizEventCrudService<BEAN, DTO, QueryDto extends PageParams> extends BizEventService {

    void doCreate(final DTO dto);

    void doUpdate(final DTO dto);

    Boolean doDelete(final List<Integer> ids);

    List<BEAN> doQuery(QueryDto queryDto);
}
