package lite.crud.config.exception;

import lite.crud.application.handler.sys.log.SystemErrorLogService;
import lite.crud.config.common.pojo.ApiResult;
import lite.crud.config.exception.handler.ExceptionHandlerConfig;
import lite.crud.config.exception.handler.ExceptionResolveHandler;
import lite.crud.config.exception.vo.ExceptionHandlerVo;
import lite.crud.domain.sys.log.enums.SystemErrorLogEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author xl-9527
 * @since 2024/8/12
 */
@RestControllerAdvice
public class ExceptionHandlerController implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    private final SystemErrorLogService systemErrorLogService;

    private final Logger log = LoggerFactory.getLogger(ExceptionHandlerController.class);

    public ExceptionHandlerController(final SystemErrorLogService systemErrorLogService) {
        this.systemErrorLogService = systemErrorLogService;
    }

    /**
     * global exception handler
     */
    @ExceptionHandler(Exception.class)
    public ApiResult<String> globalExceptionHandler(final Exception e) {
        log.error("exception: ", e);
        String finalResult = null;

        try {
            final ExceptionHandlerVo exceptionHandlerVo = ExceptionHandlerEnum.doHandler(e, applicationContext);
            finalResult = exceptionHandlerVo.getMsg();
        } catch (Exception ex) {
            log.error("resole exception error -> {}", ex.getMessage());
            finalResult = ex.getMessage();
        }
        try {
            systemErrorLogService.recordLog(e, finalResult, SystemErrorLogEnum.RESTFUL);
        } catch (Exception ex) {
            log.error("record log exception {}", ex.getMessage(), ex);
        }
        return ApiResult.fail(finalResult);
    }

    @Override
    public void setApplicationContext(@NonNull final ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * matching exception resolve handler
     */
    static class ExceptionHandlerEnum {

        @SuppressWarnings("unchecked")
        public static ExceptionHandlerVo doHandler(final Exception e, final ApplicationContext applicationContext) {
            final ExceptionHandlerVo.ExceptionHandlerVoBuilder builder = ExceptionHandlerVo.builder();
            if (ObjectUtils.isEmpty(e)) {
                return builder.msg(e.getMessage()).build();
            }

            final String msg = ExceptionHandlerConfig.getMsg(e);

            if (ObjectUtils.isEmpty(msg)) {
                // dispatch exception
                final String[] beanNamesForType = applicationContext.getBeanNamesForType(ExceptionResolveHandler.class);
                for (final String beanName : beanNamesForType) {
                    final Object bean = applicationContext.getBean(beanName);
                    if (bean instanceof ExceptionResolveHandler handler) {
                        if (handler.support(e)) {
                            return handler.resolveAndRegister(e);
                        }
                    }
                }
                builder.msg(e.getMessage());
            } else {
                builder.msg(msg);
            }

            return builder.build();
        }
    }
}
