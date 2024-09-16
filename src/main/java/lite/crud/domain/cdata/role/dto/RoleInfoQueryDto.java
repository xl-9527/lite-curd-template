package lite.crud.domain.cdata.role.dto;

import lite.crud.application.util.dto.PageParams;
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
public class RoleInfoQueryDto extends PageParams implements Serializable {

    public RoleInfoQueryDto() {
    }

    public RoleInfoQueryDto(final LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * update time
     */
    private LocalDateTime updateTime;
}
