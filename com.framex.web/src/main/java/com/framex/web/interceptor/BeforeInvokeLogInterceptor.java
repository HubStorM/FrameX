package com.framex.web.interceptor;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Component
public class BeforeInvokeLogInterceptor implements Interceptor {

    @Override
    public int setInterceptOrder() {
        return 2;
    }

    @Override
    public TargetControllerDefinition setTargetController() {
        return new TargetControllerDefinition(null, true);
    }

    @Override
    public TargetMethodDefinition setTargetMethod() {
        return new TargetMethodDefinition("init", null, true, false, true);
    }

    @Override
    public Map<Object, Object> intercept(Map<Object, Object> input, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        System.out.println("before method init invoke");
        return null;
    }

}
