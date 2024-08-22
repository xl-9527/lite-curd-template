package lite.crud.application.util.log.record.bean;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author xl-9527
 * @since 2024/8/22
 */
@Getter
@Setter
public class LogRecordBean {

	public LogRecordBean() {
	}

	public LogRecordBean(final String dataId, final String fieldName,
						 final String field, final Object beforeValue, final Object afterValue,
						 final String userName, final Date createAt) {
		this.dataId = dataId;
		this.fieldName = fieldName;
		this.field = field;
		this.beforeValue = beforeValue;
		this.afterValue = afterValue;
		this.userName = userName;
		this.createAt = createAt;
	}

	/**
	 * 数据id，平台生成的id
	 */
	private String dataId;

	/**
	 * 字段名称
	 */
	private String fieldName;

	/**
	 * 字段
	 */
	private String field;

	/**
	 * 改前值
	 */
	private Object beforeValue;

	/**
	 * 改后值
	 */
	private Object afterValue;

	/**
	 * 用户
	 */
	private String userName;

	/**
	 * 操作时间
	 */
	private Date createAt;
}
