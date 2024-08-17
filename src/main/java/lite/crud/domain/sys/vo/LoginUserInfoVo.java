package lite.crud.domain.sys.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author xl-9527
 * @since 2024/7/27
 **/
//@Getter
//@Setter
public record LoginUserInfoVo(Integer id, String username, String password) implements Serializable {
}
