package com.framex.web.interceptor;

import java.util.List;

/**
 * 目标方法定义类。
 * 用于interceptor接口定义目标方法。
 *
 * @version [版本号, 2017年7月31日]
 * @作者 undefined
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class TargetMethodDefinition {
    private String methodName;
    private List<Class<?>> argumentsType;
    private boolean interceptAllControllers;
    private boolean interceptAllMethod;
    private boolean interceptAllMethodByName;

    public TargetMethodDefinition() {
        super();
    }


    public TargetMethodDefinition(String methodName, List<Class<?>> argumentsType, boolean interceptAllControllers,
                                  boolean interceptAllMethod, boolean interceptAllMethodByName) {
        super();
        this.methodName = methodName;
        this.argumentsType = argumentsType;
        this.interceptAllControllers = interceptAllControllers;
        this.interceptAllMethod = interceptAllMethod;
        this.interceptAllMethodByName = interceptAllMethodByName;
    }


    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public List<Class<?>> getArgumentsType() {
        return argumentsType;
    }

    public void setArgumentsType(List<Class<?>> argumentsType) {
        this.argumentsType = argumentsType;
    }

    public boolean isInterceptAllControllers() {
        return interceptAllControllers;
    }

    public void setInterceptAllControllers(boolean interceptAllControllers) {
        this.interceptAllControllers = interceptAllControllers;
    }

    public boolean isInterceptAllMethodByName() {
        return interceptAllMethodByName;
    }

    public void setInterceptAllMethodByName(boolean interceptAllMethodByName) {
        this.interceptAllMethodByName = interceptAllMethodByName;
    }


    public boolean isInterceptAllMethod() {
        return interceptAllMethod;
    }


    public void setInterceptAllMethod(boolean interceptAllMethod) {
        this.interceptAllMethod = interceptAllMethod;
    }


}
