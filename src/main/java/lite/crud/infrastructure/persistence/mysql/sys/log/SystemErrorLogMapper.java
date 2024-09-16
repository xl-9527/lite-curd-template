package lite.crud.infrastructure.persistence.mysql.sys.log;

import lite.crud.domain.sys.log.bo.SystemErrorLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author xl-9527
 * @since 2024/9/16
 **/
@Mapper
public interface SystemErrorLogMapper {

    Boolean doSave(SystemErrorLog systemErrorLog);
}
