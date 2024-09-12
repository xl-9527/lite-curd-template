package lite.crud.application.handler.cdata.role.support;

import lite.crud.application.base.BizEventCrudService;
import lite.crud.config.common.pojo.Page;
import lite.crud.domain.cdata.role.dto.RoleInfoQueryDto;
import lite.crud.domain.cdata.role.dto.RoleInfoWriteDto;
import lite.crud.domain.cdata.role.vo.RoleInfoVo;
import lite.crud.infrastructure.persistence.mysql.cdata.role.RoleInfoMapper;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author xl-9527
 * @since 2024/9/7
 **/
@Service
public class RoleInfoCrudServiceSupport implements BizEventCrudService<RoleInfoVo, RoleInfoWriteDto, RoleInfoQueryDto> {

    private final RoleInfoMapper roleInfoMapper;

    public RoleInfoCrudServiceSupport(final RoleInfoMapper roleInfoMapper) {
        this.roleInfoMapper = roleInfoMapper;
    }

    @Override
    public Boolean doSave(final RoleInfoWriteDto roleInfoWriteDto) {
        return Boolean.TRUE;
    }

    @Override
    public void doUpdate(final RoleInfoWriteDto updateMap) {

    }

    @Override
    public void doUpdate(final Map<String, Object> updateMap) {

    }

    @Override
    public Boolean doDelete(final List<Integer> ids) {
        return null;
    }

    @Override
    public List<RoleInfoVo> doGetByIds(final Serializable[] ids) {
        return List.of();
    }

    @Override
    public RoleInfoVo doGetById(final Serializable id) {
        return null;
    }

    @Override
    public List<RoleInfoVo> doQueryListPage(final Page<RoleInfoVo> page, final RoleInfoQueryDto roleInfoQueryDto) {
        return roleInfoMapper.doQueryListPage(page, roleInfoQueryDto);
    }

    @Override
    public List<RoleInfoVo> doQuery(final RoleInfoQueryDto roleInfoQueryDto) {
        return roleInfoMapper.doQuery(roleInfoQueryDto);
    }
}
