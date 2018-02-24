package com.charmingglobe.gr.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by PANZHENG on 2018/2/8.
 */
public class UserRequestAspect {

    @Pointcut("execution(* com.charmingglobe.gr.service.UserRequestService.submitUserRequest(..))")
    public void submitUserRequest() {}

    @After("submitUserRequest()")
    public void addUserAction() {

        System.out.println("11111111111111111111111111111111111");
        System.out.println("11111111111111111111111111111111111");
        System.out.println("11111111111111111111111111111111111");
    }
}
