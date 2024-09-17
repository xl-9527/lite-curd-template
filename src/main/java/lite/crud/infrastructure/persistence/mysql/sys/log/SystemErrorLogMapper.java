package lite.crud.infrastructure.persistence.mysql.sys.log;

import lite.crud.config.common.pojo.Page;
import lite.crud.domain.sys.log.bo.SystemErrorLog;
import lite.crud.domain.sys.log.dto.SystemErrorLogQueryDto;
import lite.crud.domain.sys.log.vo.SystemErrorLogVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xl-9527
 * @since 2024/9/16
 **/
@Mapper
public interface SystemErrorLogMapper {

    Boolean doSave(SystemErrorLog systemErrorLog);

    List<SystemErrorLogVo> doQueryListPage(Page<SystemErrorLogVo> page, @Param("dto") SystemErrorLogQueryDto systemErrorLogQueryDto);
}
