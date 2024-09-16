package lite.crud.application.util.log;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import lite.crud.application.util.extend.UserInfoUtil;
import lite.crud.application.util.opc.json.JSONOpcUtil;
import lite.crud.domain.sys.login.vo.LoginUserInfoVo;
import org.apache.commons.lang3.ObjectUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.security.auth.login.FailedLoginException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Aspect
@Component
public class AopLog {

    private final Logger log = LoggerFactory.getLogger(AopLog.class);

    @Pointcut(value = "execution(* lite.crud.interfaces..*.*(..))")
    public void logInit() {
    }

    @Around(value = "logInit()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            return joinPoint.proceed();
        } finally {
            long took = (System.currentTimeMillis() - start) / 1000;
            if (took >= 2) {
                log.info("exec time out：{} s", took);
            } else {
                log.debug("exec time：{} s", took);
            }
        }
    }

    @AfterReturning(pointcut = "logInit()")
    public void logAfterReturn(JoinPoint joinPoint) throws FailedLoginException {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        final Object[] newArgs = this.canSerializeArgs(args);
        final LoginUserInfoVo currentLoginUserInfoVo = UserInfoUtil.getCurrentLoginUserInfoVo();
        log.info("handler-method -> {}, params -> {}, login user -> {}", methodName, JSONOpcUtil.DEFAULT.toJSONStr(newArgs), currentLoginUserInfoVo.getUsername());
    }

    @AfterThrowing(pointcut = "logInit()")
    public void logAfterReturnThrow(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        try {
            final Object[] newArgs = this.canSerializeArgs(args);
            log.error("发生异常 => {} 参数 => {}", joinPoint.getTarget(), newArgs);
        } catch (Exception e) {
            log.error("发生异常 => {}", joinPoint.getTarget());
        }
    }

    private Object[] canSerializeArgs(final Object[] args) {
        if (ObjectUtils.isEmpty(args)) {
            return args;
        }

        // core logic
        final List<Object> result = new ArrayList<>(args.length);
        for (final Object arg : args) {
            if (
                    arg instanceof MultipartFile || arg instanceof MultipartFile[] || arg instanceof ServletRequest ||
                            arg instanceof ServletResponse || arg instanceof OutputStream
            ) {
                continue;
            }

            result.add(arg);
        }

        return result.toArray(new Object[0]);
    }
}
