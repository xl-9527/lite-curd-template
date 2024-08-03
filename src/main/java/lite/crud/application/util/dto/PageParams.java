package lite.crud.application.util.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author xl-9527
 * @since 2024/8/2
 */
@Getter
@Setter
public class PageParams implements Serializable {

    /**
     * 分页
     */
    private Integer pageIndex;

    /**
     * 分页大小
     */
    private Integer pageSize;
}
