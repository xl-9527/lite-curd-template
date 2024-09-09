package lite.crud.application.util.dto;

import lite.crud.config.common.pojo.Page;
import lite.crud.config.exception.custom.BusinessException;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ObjectUtils;

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

	public <T> Page<T> toPage(final Class<T> clazz) {
		if (ObjectUtils.isNotEmpty(clazz)) {
			throw new BusinessException("pageParams.toPage.error, clazz is null");
		}
		return new Page<>(pageIndex, pageSize);
	}
}
