package lite.crud.domain.cdata.user.dto;

import lite.crud.application.util.dto.PageParams;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author xl-9527
 * @since 2024/8/17
 */
@Getter
@Setter
public class UserInfoQueryDto extends PageParams implements Serializable {

    public UserInfoQueryDto() {
    }

    public UserInfoQueryDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Login username for this
     */
    private String username;

    /**
     * login password
     */
    private String password;


	public UserInfoQueryDtoBuilder builder() {
		return new UserInfoQueryDtoBuilder(this);
	}

	public static class UserInfoQueryDtoBuilder {

		private final UserInfoQueryDto userInfoQueryDto;

		private UserInfoQueryDtoBuilder(UserInfoQueryDto userInfoQueryDto) {
			this.userInfoQueryDto = userInfoQueryDto;
		}

		public UserInfoQueryDtoBuilder username(String username) {
			userInfoQueryDto.username = username;
			return this;
		}

		public UserInfoQueryDtoBuilder password(String password) {
			userInfoQueryDto.password = password;
			return this;
		}

		public UserInfoQueryDtoBuilder ids(Serializable[] ids) {
			userInfoQueryDto.setIds(ids);
			return this;
		}

		public UserInfoQueryDto build() {
			return userInfoQueryDto;
		}
	}
}
