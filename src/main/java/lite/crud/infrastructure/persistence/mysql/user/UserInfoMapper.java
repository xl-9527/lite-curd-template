package lite.crud.infrastructure.persistence.mysql.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lite.crud.domain.user.bo.UserInfo;
import lite.crud.domain.user.dto.UserInfoQueryDto;
import lite.crud.domain.user.vo.UserInfoVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author xl-9527
 * @since 2024/8/17
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

	List<UserInfoVo> doQuery(UserInfoQueryDto userInfoQueryDto);
}
