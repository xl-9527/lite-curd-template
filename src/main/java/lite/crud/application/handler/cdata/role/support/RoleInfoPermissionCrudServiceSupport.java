package lite.crud.application.handler.cdata.role.support;

import lite.crud.application.base.BizEventCrudService;
import lite.crud.config.common.pojo.Page;
import lite.crud.config.exception.custom.BusinessException;
import lite.crud.domain.cdata.role.bo.RoleInfoPermission;
import lite.crud.domain.cdata.role.dto.RoleInfoPermissionQueryDto;
import lite.crud.domain.cdata.role.vo.RoleInfoPermissionVo;
import lite.crud.infrastructure.persistence.mysql.cdata.role.RoleInfoPermissionMapper;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author xl-9527
 * @since 2024/9/12
 **/
@Service
public class RoleInfoPermissionCrudServiceSupport implements BizEventCrudService<RoleInfoPermissionVo, RoleInfoPermission, RoleInfoPermissionQueryDto> {

    private final RoleInfoPermissionMapper roleInfoPermissionMapper;

    public RoleInfoPermissionCrudServiceSupport(final RoleInfoPermissionMapper roleInfoPermissionMapper) {
        this.roleInfoPermissionMapper = roleInfoPermissionMapper;
    }

    @Override
    public Boolean doSave(final RoleInfoPermission roleInfoPermission) {
        return Boolean.TRUE;
    }

    @Override
    public void doUpdate(final RoleInfoPermission updateMap) {

    }

    @Override
    public void doUpdate(final Map<String, Object> updateMap) {

    }

    @Override
    public Boolean doDelete(final List<Integer> ids) {
        if (ObjectUtils.isEmpty(ids)) {
            throw new BusinessException("ids is empty");
        }
        return roleInfoPermissionMapper.delete(ids);
    }

    @Override
    public List<RoleInfoPermissionVo> doGetByIds(final Serializable[] ids) {
        return List.of();
    }

    @Override
    public RoleInfoPermissionVo doGetById(final Serializable id) {
        if (id == null) {
            throw new BusinessException("id is null");
        }
        return roleInfoPermissionMapper.getById(id);
    }

    @Override
    public List<RoleInfoPermissionVo> doQueryListPage(final Page<RoleInfoPermissionVo> page, final RoleInfoPermissionQueryDto roleInfoPermissionQueryDto) {
        return List.of();
    }

    @Override
    public List<RoleInfoPermissionVo> doQuery(final RoleInfoPermissionQueryDto roleInfoPermissionQueryDto) {
        return roleInfoPermissionMapper.doQuery(roleInfoPermissionQueryDto);
    }
}
