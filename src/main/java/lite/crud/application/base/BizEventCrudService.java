package lite.crud.application.base;

import lite.crud.application.BizEventService;
import lite.crud.application.util.dto.PageParams;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author xl-9527
 * @since 2024/8/2
 */
public interface BizEventCrudService<VO, DTO, QueryDto extends PageParams> extends BizEventService {

    void doCreate (final DTO dto);

    void doUpdate(final DTO updateMap);

    void doUpdate(final Map<String, Object> updateMap);

    Boolean doDelete(final List<Integer> ids);

    List<VO> doGetByIds(final Serializable[] ids);

    VO doGetById(final Serializable id);

    List<VO> doQuery(QueryDto queryDto);
}
