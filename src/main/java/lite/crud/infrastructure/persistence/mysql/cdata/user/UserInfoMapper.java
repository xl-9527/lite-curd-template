package lite.crud.infrastructure.persistence.mysql.cdata.user;

import lite.crud.config.common.pojo.Page;
import lite.crud.domain.cdata.user.bo.UserInfo;
import lite.crud.domain.cdata.user.dto.UserInfoQueryDto;
import lite.crud.domain.cdata.user.dto.UserInfoWriteDto;
import lite.crud.domain.cdata.user.vo.UserInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xl-9527
 * @since 2024/8/17
 */
@Mapper
public interface UserInfoMapper {

    List<UserInfoVo> doQuery(Page<UserInfoVo> page, @Param("dto") UserInfoQueryDto userInfoQueryDto);

    Boolean doSave(UserInfo dbBean);

    int deleteByIds(List<Integer> ids);

    void doUpdate(UserInfoWriteDto updateMap);
}
