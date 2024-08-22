package lite.crud.application.util.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//@Aspect
@Component
public class AopLog {

	private final Logger log = LoggerFactory.getLogger(AopLog.class);

//	@Pointcut(value = "execution(* lite.crud.interfaces..*.*(..))")
//	public void logInit() {
//	}
//
//	@Around(value = "logInit()")
//	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
//		long start = System.currentTimeMillis();
//		try {
//			return joinPoint.proceed();
//		} finally {
//			long took = (System.currentTimeMillis() - start) / 1000;
//			if (took >= 2) {
//				log.info("方法执行超时：{} s", took);
//			} else {
//				log.debug("方法执行超时：{} s", took);
//			}
//		}
//	}
//
//	@AfterReturning(pointcut = "logInit()")
//	public void logAfterReturn(JoinPoint joinPoint) {
//		String methodName = joinPoint.getSignature().getName();
//		Object[] args = joinPoint.getArgs();
//		JSONArray queryParams = new JSONArray();
//		for (Object arg : args) {
//			if (!(arg instanceof MultipartFile)) {
//				queryParams.add(arg);
//			} else {
//				log.info("上传文件的参数直接忽略");
//			}
//		}
//		if (queryParams == null || queryParams.size() == 0) {
//			try {
//				String userName = NewUserUtils.getUser().getCodeName();
//				log.info("登录用户：{}，访问方法：{}，请求参数：{}", userName, methodName, queryParams);
//			} catch (BusinessException e) {
//				log.warn("用户没有登陆，错误信息 => {}，访问方法：{}，请求参数：{}", e.getMessage(), methodName, queryParams);
//			} catch (Exception e) {
//				log.error("获取用户信息异常", e);
//			}
//		} else {
//			String userName = queryParams.stream().filter(item -> (item instanceof JSONObject) && ((JSONObject) item).containsKey("newUser")).map(item -> ((JSONObject) item).getJSONObject("newUser").getString("codeName")).findFirst().orElse("");
//			if (ObjectUtils.isEmpty(userName)) {
//				try {
//					userName = NewUserUtils.getUser().getCodeName();
//					String string = queryParams.toString();
//					log.info("登录用户：{}，访问方法：{}，请求参数：{}", userName, methodName, string);
//				} catch (BusinessException e) {
//					try {
//						String string = queryParams.toString();
//						log.warn("用户没有登陆，错误信息 => {}，访问方法：{}，请求参数：{}", e.getMessage(), methodName, string);
//					} catch (Exception ce) {
//						log.warn("用户没有登陆，错误信息 => {}，访问方法：{}", e.getMessage(), methodName);
//					}
//				} catch (Exception e) {
//					log.error("获取用户信息异常 {}", e.getMessage());
//				}
//			}
//		}
//	}
//
//	@AfterThrowing(pointcut = "logInit()")
//	public void logAfterReturnThrow(JoinPoint joinPoint) {
//		Object[] args = joinPoint.getArgs();
//		JSONArray queryParams = new JSONArray();
//		for (Object arg : args) {
//			if (!(arg instanceof MultipartFile)) {
//				queryParams.add(arg);
//			} else {
//				log.info("上传文件的参数直接忽略");
//			}
//		}
//		try {
//			String string = queryParams.toString();
//			log.error("发生异常 => {} 参数 => {}", joinPoint.getTarget(), string);
//		} catch (Exception e) {
//			log.error("发生异常 => {}", joinPoint.getTarget());
//		}
//	}
}
