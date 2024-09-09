package lite.crud.config.common.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author xl-9527
 * @since 2024/9/6
 */
@Getter
@Setter
public class Page<T> {

    public Page() {
    }

    public Page(final Integer current, final Integer size) {
        if (current == null || current < 1) {
            this.current = 1;
        } else {
            this.current = current;
        }
        if (size == null || size < 10) {
            this.size = 10;
        } else {
            this.size = size;
        }
    }

    /**
     * current with page,
     * default is 1
     */
    private Integer current;

    /**
     * current with size
     * default is 10
     */
    private Integer size;

    /**
     * total record
     */
    private Integer total;

    /**
     * page query result
     */
    private List<T> record;

    /**
     * instance one empty page
     */
    public static <T> Page<T> empty() {
        return new Page<>();
    }
}
