package com.etranzact.dris.authservice.dris.authservice.Util.AsyncExceptionHandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Slf4j
@Component
public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {


    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... args) {
        log.info("========= An Error Occurred While  Executing an Async Method");
        log.warn("Method name: " +  method.getName() );
        log.warn("Method Args: " + Arrays.toString(args));
        log.warn("Async Error : " +  ex.getMessage());
    }
}
