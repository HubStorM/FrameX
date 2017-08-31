package com.framex.web.handler;

import java.lang.reflect.Method;
import java.util.List;
import com.framex.web.interceptor.InterceptorModel;

/**
 * 请求处理方法包装类。
 *
 * @version [版本号, 2017年7月30日]
 * @作者 undefined
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */

@SuppressWarnings("rawtypes")
public class InvokeInfo {
    private Method targetMethod;

    private List<Object> arguments;

    private List<InterceptorModel> interceptors;


    public InvokeInfo(Method targetMethod, List<InterceptorModel> interceptors) {
        super();
        this.targetMethod = targetMethod;
        this.interceptors = interceptors;
    }


    public InvokeInfo(Method targetMethod, List<Object> arguments, List<InterceptorModel> interceptors) {
        super();
        this.targetMethod = targetMethod;
        this.arguments = arguments;
        this.interceptors = interceptors;
    }


    public Method getTargetMethod() {
        return targetMethod;
    }


    public void setTargetMethod(Method targetMethod) {
        this.targetMethod = targetMethod;
    }


    public List<Object> getArguments() {
        return arguments;
    }


    public void setArguments(List<Object> arguments) {
        this.arguments = arguments;
    }


    public List<InterceptorModel> getInterceptors() {
        return interceptors;
    }


    public void setInterceptors(List<InterceptorModel> interceptors) {
        this.interceptors = interceptors;
    }


    @Override
    public String toString() {
        return "InvokeInfo...targetMthod:" + this.targetMethod.getName() + ";arguments:" + this.arguments + ";interceptors:" + this.interceptors;
    }


}
