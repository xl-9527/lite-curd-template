package lite.crud.application.util.dto;

import lite.crud.domain.cdata.user.vo.UserInfoVo;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author xl-9527
 * @since 2024/8/17
 */
@Getter
@Setter
public class BaseParams implements Serializable {

	/**
	 * 当前登录用户
	 */
	private UserInfoVo userInfoVo;

	/**
	 * 主键 IDS
	 */
	private Serializable[] ids;
}
