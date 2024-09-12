package lite.crud.infrastructure.persistence.mysql.cdata.role;

import lite.crud.config.common.pojo.Page;
import lite.crud.domain.cdata.role.dto.RoleInfoQueryDto;
import lite.crud.domain.cdata.role.vo.RoleInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xl-9527
 * @since 2024/9/9
 */
@Mapper
public interface RoleInfoMapper {

    List<RoleInfoVo> doQueryListPage(Page<RoleInfoVo> page, @Param("dto") RoleInfoQueryDto roleInfoQueryDto);

    List<RoleInfoVo> doQuery(RoleInfoQueryDto roleInfoQueryDto);
}
