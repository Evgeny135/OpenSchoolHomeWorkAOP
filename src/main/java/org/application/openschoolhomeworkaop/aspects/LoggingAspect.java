package org.application.openschoolhomeworkaop.aspects;

import org.application.openschoolhomeworkaop.exceptions.ElementNotFoundException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(* org.application.openschoolhomeworkaop.services.*.*(..))")
    private void allMethodLogging(){}

    @Pointcut("execution(* org.application.openschoolhomeworkaop.services.*.get*(..))")
    private void getMethodLogging() {
    }

//    @Pointcut("execution(* org.application.openschoolhomeworkaop.services.*.*(..)) && !getMethodLogging()")
//    private void otherMethodLogging() {
//    }

    @Before("allMethodLogging()")
    public void loggingBeforeMethods(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        log.info("Вызов метода {}", signature.getName());
        for (int i = 0; i < joinPoint.getArgs().length; i++) {
            log.info("Параметр {}: {}", i+1, joinPoint.getArgs()[i]);
        }
    }

    @AfterThrowing(pointcut = "allMethodLogging()", throwing = "exception")
    public void loggingAfterThrowing(Throwable exception){
        log.error("Выброс исключения: {}", exception.getMessage());
    }

    @AfterReturning(pointcut = "getMethodLogging()", returning = "result")
    public void loggingAfterReturning(Object result){
        log.info("Метод вернул: {}", result.toString());
    }

//    @Around("getMethodLogging()")
//    public Object loggingGetMethods(ProceedingJoinPoint joinPoint) throws Throwable {
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        log.info("Вызов {}", signature.getName());
//        for (int i = 0; i < joinPoint.getArgs().length; i++) {
//            log.info("Параметр {}: {}",i+1 ,joinPoint.getArgs()[i]);
//        }
//        try {
//            Object result = joinPoint.proceed();
//            log.info("Возврат {}", result.toString());
//            return result;
//        } catch (Exception e) {
//            log.info("Вызов исключения {}", e.toString());
//            throw e;
//        }
//    }
//
//    @Around("otherMethodLogging()")
//    public void loggingOtherMethods(ProceedingJoinPoint joinPoint) throws Throwable {
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        log.info("Вызов {}", signature.getName());
//        for (int i = 0; i < joinPoint.getArgs().length; i++) {
//            log.info("Параметр {}: {}",i+1 ,joinPoint.getArgs()[i]);
//        }
//        try {
//            joinPoint.proceed();
//        } catch (Exception e) {
//            log.info("Вызов исключен+ия {}", e.getMessage());
//        }
//    }
}
