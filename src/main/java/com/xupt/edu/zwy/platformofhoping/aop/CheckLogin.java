package com.xupt.edu.zwy.platformofhoping.aop;

import com.xupt.edu.zwy.platformofhoping.common.CommonJsonResult;
import com.xupt.edu.zwy.platformofhoping.enums.ReturnCodes;
import com.xupt.edu.zwy.platformofhoping.util.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;


@Slf4j
//@Aspect
//@Component
public class CheckLogin implements Ordered {

    /**
     * Controller方法名
     */
    private String methodName;


    /**
     * 切入点
     */
    @Pointcut("execution(public * com.xupt.edu.zwy.platformofhoping.controller.*.*(..)) " +
            "&& !execution(public * com.xupt.edu.zwy.platformofhoping.controller.UserController.login(..)) " +
            "&& !execution(public * com.xupt.edu.zwy.platformofhoping.controller.UserController.registerUser(..))" +
            "&& !execution(public * com.xupt.edu.zwy.platformofhoping.controller.UserController.destory(..))" +
            "&& !execution(public * com.xupt.edu.zwy.platformofhoping.controller.OrganizerController.organizerLogin(..))" +
            "&& !execution(public * com.xupt.edu.zwy.platformofhoping.controller.OrganizerController.orgainzerRegister(..))")
    public void aopLoginPointCut() {
    }


    /**
     * 检查是否登录
     *
     * @param
     */
    @Around("aopLoginPointCut()")
    public Object checkLogin(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        /*methodName = proceedingJoinPoint.getSignature().getDeclaringTypeName() + "." + proceedingJoinPoint.getSignature().getName();*/
        methodName = new StringBuilder(proceedingJoinPoint.getSignature().getDeclaringTypeName()).append(".")
                .append(proceedingJoinPoint.getSignature().getName()).toString();
        log.info("执行方法{}校验是否登录", methodName);
        try {
             String userName = RequestUtil.getLoginUserName();
            if (StringUtils.isBlank(userName)) {
                log.info("未登录！！！");
                return CommonJsonResult.fail(ReturnCodes.NOT_LOGIN, "未登录");
            }
            log.info("登陆成功！！！");
        } catch (Exception e) {
            log.info("用户信息已过期：{}", e.getMessage());
            return CommonJsonResult.fail(ReturnCodes.NOT_LOGIN, e.getMessage() );
        }
        Object obj;
        try {
            obj = proceedingJoinPoint.proceed();
        }catch (Throwable throwable){
            log.error("exception when excute  ChekLogin:proceedingJoinPoint.proceed()");
            String puffix = "check_login_into";
            String monitorName  = new StringBuilder(puffix).append(methodName).toString();
            throw throwable;
        }
        return obj;
    }

    @Override
    public int getOrder() {
        return 2;
    }

}
