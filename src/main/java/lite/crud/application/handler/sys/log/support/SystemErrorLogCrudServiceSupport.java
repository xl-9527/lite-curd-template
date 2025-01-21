package lite.crud.application.handler.sys.log.support;

import lite.crud.application.base.BizEventCrudService;
import lite.crud.config.common.pojo.Page;
import lite.crud.domain.sys.log.bo.SystemErrorLog;
import lite.crud.domain.sys.log.dto.SystemErrorLogQueryDto;
import lite.crud.domain.sys.log.vo.SystemErrorLogVo;
import lite.crud.infrastructure.persistence.mysql.sys.log.SystemErrorLogMapper;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author xl-9527
 * @since 2024/9/16
 **/
@Service
public class SystemErrorLogCrudServiceSupport implements BizEventCrudService<SystemErrorLogVo, SystemErrorLog, SystemErrorLogQueryDto> {

    private final SystemErrorLogMapper systemErrorLogMapper;

    public SystemErrorLogCrudServiceSupport(final SystemErrorLogMapper systemErrorLogMapper) {
        this.systemErrorLogMapper = systemErrorLogMapper;
    }

    @Override
    public Boolean doSave(final SystemErrorLog systemErrorLog) {
        return systemErrorLogMapper.doSave(systemErrorLog);
    }

    @Override
    public void doUpdate(final SystemErrorLog updateMap) {

    }

    @Override
    public void doUpdate(final Map<String, Object> updateMap) {

    }

    @Override
    public Boolean doDelete(final List<Integer> ids) {
        return null;
    }

    @Override
    public List<SystemErrorLogVo> doGetByIds(final Serializable[] ids) {
        return List.of();
    }

    @Override
    public SystemErrorLogVo doGetById(final Serializable id) {
        return null;
    }

    @Override
    public List<SystemErrorLogVo> doQueryListPage(final Page<SystemErrorLogVo> page, final SystemErrorLogQueryDto systemErrorLogQueryDto) {
        return systemErrorLogMapper.doQueryListPage(page, systemErrorLogQueryDto);
    }

    @Override
    public List<SystemErrorLogVo> doQuery(final SystemErrorLogQueryDto systemErrorLogQueryDto) {
        return List.of();
    }
}
