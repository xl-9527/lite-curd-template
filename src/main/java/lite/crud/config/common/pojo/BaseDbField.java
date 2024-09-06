package lite.crud.config.common.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author xl-9527
 * @since 2024/9/6
 */
@Getter
@Setter
public class BaseDbField implements Serializable {

    /**
     * create time
     */
    private LocalDateTime createTime;

    /**
     * update time
     */
    private LocalDateTime updateTime;

    /**
     * create user info -> common is current login, this is opc user
     */
    private String createBy;

    /**
     * update user info -> common is current login, this is opc user
     */
    private String updateBy;
}
