package lite.crud.config.exception.handler.resolve;

import lite.crud.config.exception.handler.AbsExceptionHandler;
import lite.crud.config.exception.vo.ExceptionHandlerVo;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.stream.Collectors;

/**
 * @author xl-9527
 * @since 2024/8/21
 */
@Component
public class MethodArgumentNotValidExceptionResolve extends AbsExceptionHandler<MethodArgumentNotValidException> {

	protected MethodArgumentNotValidExceptionResolve() {
		super(MethodArgumentNotValidException.class);
	}

	@Override
	public ExceptionHandlerVo resolve(MethodArgumentNotValidException exception) {
		BindingResult bindingResult = exception.getBindingResult();
		final String errorMsg = bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
				.collect(Collectors.joining(","));
		return ExceptionHandlerVo.builder().msg(errorMsg).build();
	}
}
