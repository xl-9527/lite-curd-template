package lite.crud.application.util.dto;

import lite.crud.config.common.pojo.Page;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author xl-9527
 * @since 2024/8/2
 */
@Getter
@Setter
public class PageParams extends BaseParams implements Serializable {

    /**
     * 分页
     */
    private Integer pageIndex;

    /**
     * 分页大小
     */
    private Integer pageSize;

    /**
     * get a page with current dto
     * @return <pre>Page</pre>
     */
    public <T> Page<T> toPage() {
        return new Page<>(pageIndex, pageSize);
    }
}
