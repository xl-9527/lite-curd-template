package lite.crud.config.exception.handler.resolve;

import lite.crud.config.exception.handler.AbsExceptionHandler;
import lite.crud.config.exception.vo.ExceptionHandlerVo;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.ibatis.binding.BindingException;
import org.springframework.stereotype.Component;

/**
 * @author xl-9527
 * @since 2024/8/17
 */
@Component
public class BindingExceptionResolve extends AbsExceptionHandler<BindingException> {

	protected BindingExceptionResolve() {
		super(BindingException.class);
	}

	@Override
	public ExceptionHandlerVo resolve(BindingException exception) {
		String eMsg = exception.getMessage();
		final StringBuilder eMsgBuild = new StringBuilder("Mybatis XML 和 Mapper 绑定异常");
		if (ObjectUtils.isNotEmpty(eMsg)) {
			eMsgBuild.append("：");
			if (eMsg.contains("(not found)")) {
				eMsgBuild.append("【没有找到对应的 mapper】.");
			}
			eMsgBuild.append("详细的报错信息 -> [").append(eMsg).append("]");
		}
		return ExceptionHandlerVo.builder().msg(eMsgBuild.toString()).build();
	}
}
