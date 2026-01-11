package mn.io.polaris.configuration;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Aspect
@Configuration
@Log4j2
public class ApplicationLoggingAspect {

    @Before("execution(* mn.io.polaris.controller.*.*(..))")
    public void logBeforeMethod(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        Object[] args = jp.getArgs();
        log.info("---------- Start of " + jp.getTarget().getClass().getSimpleName() + "_" + methodName + " ----------");
        log.info("args: " + Arrays.toString(args));
    }

    @AfterReturning("execution(* mn.io.polaris.controller.*.*(..))")
    public void logAfterReturningMethod(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        log.info("---------- End of " + jp.getTarget().getClass().getSimpleName() + "_" + methodName + " ----------");
    }

    @AfterThrowing(value = "execution(* mn.io.polaris.controller.*.*(..))", throwing = "ex")
    public void logAfterThrowing(Exception ex) {
        log.error("An exception occurred: " + ex);
    }

}
