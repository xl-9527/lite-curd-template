package lite.crud.application.util.log;

import lite.crud.application.util.opc.json.JSONOpcUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

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
				log.info("方法执行超时：{} s", took);
			} else {
				log.debug("方法执行超时：{} s", took);
			}
		}
	}

	@AfterReturning(pointcut = "logInit()")
	public void logAfterReturn(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		Object[] args = joinPoint.getArgs();
		log.info("methodName -> {}, 参数 -> {}", methodName, JSONOpcUtil.DEFAULT.toJSONStr(args));
	}

	@AfterThrowing(pointcut = "logInit()")
	public void logAfterReturnThrow(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		List<Object> queryParams = new ArrayList<>();
		for (Object arg : args) {
			if (!(arg instanceof MultipartFile)) {
				queryParams.add(arg);
			} else {
				log.info("上传文件的参数直接忽略");
			}
		}
		try {
			String string = queryParams.toString();
			log.error("发生异常 => {} 参数 => {}", joinPoint.getTarget(), string);
		} catch (Exception e) {
			log.error("发生异常 => {}", joinPoint.getTarget());
		}
	}
}
