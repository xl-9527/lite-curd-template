package lite.crud.infrastructure.persistence.mysql;

import lite.crud.application.base.BizEventCrudService;
import lite.crud.application.util.dto.PageParams;
import lite.crud.config.common.pojo.Page;
import lite.crud.domain.cdata.user.dto.UserInfoQueryDto;
import lite.crud.domain.cdata.user.vo.UserInfoVo;
import lite.crud.infrastructure.InvokeInfrastructure;

/**
 * @author xl-9527
 * @since 2024/8/27
 */
public abstract class MysqlInvokeInfrastructure<QueryDto extends PageParams, VO, T extends BizEventCrudService<VO, ?, QueryDto>> implements InvokeInfrastructure<T> {

    protected final T crudService;

    public MysqlInvokeInfrastructure(final T crudService) {
        this.crudService = crudService;
    }

    @Override
    public T invoke() {
        return crudService;
    }

    public abstract Page<VO> queryListPage(final Page<VO> page, final QueryDto queryDto);
}
