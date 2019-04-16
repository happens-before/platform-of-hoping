package com.xupt.edu.zwy.platformofhoping.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class MethodRunningTime implements Ordered {

    /**
     * Controller方法名
     */
    private String controllerMethodName;

    /**
     * Controller开始时间
     */
    private long controllerStartTime;


    /**
     * 切入点
     */
    @Pointcut("execution(public * com.xupt.edu.zwy.platformofhoping.controller.*.*(..))")
    public void aopControllerPointCut() {
    }

    /**
     * Controller方法执行前计时
     *
     * @param joinPoint
     */
    @Before("aopControllerPointCut()")
    public void doControllerBefore(JoinPoint joinPoint) {
        controllerMethodName= new StringBuilder(joinPoint.getSignature().getDeclaringTypeName()).append(".")
                .append(joinPoint.getSignature().getName()).toString();
        controllerStartTime = System.currentTimeMillis();
        log.info("执行方法{}计时:{}" ,controllerMethodName);
    }

    /**
     * Controller方法执行后计时
     */
    @After("aopControllerPointCut()")
    public void doControllerAfter() {
        long E_time = System.currentTimeMillis() - controllerStartTime;
        log.info("执行方法{} 耗时为：{}ms",controllerMethodName,E_time);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
