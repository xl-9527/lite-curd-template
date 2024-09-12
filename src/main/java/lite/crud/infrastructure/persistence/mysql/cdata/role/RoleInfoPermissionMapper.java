package lite.crud.infrastructure.persistence.mysql.cdata.role;

import lite.crud.domain.cdata.role.dto.RoleInfoPermissionQueryDto;
import lite.crud.domain.cdata.role.vo.RoleInfoPermissionVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * @author xl-9527
 * @since 2024/9/12
 **/
@Mapper
public interface RoleInfoPermissionMapper {

    RoleInfoPermissionVo getById(Serializable id);

    Boolean delete(List<Integer> ids);

    List<RoleInfoPermissionVo> doQuery(@Param("dto") RoleInfoPermissionQueryDto roleInfoPermissionQueryDto);
}
