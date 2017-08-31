/*
 * 根据controller生成的url规则，FrameXHandlerMapping根据匹配request和该类定位controller方法。
 */
package com.framex.web.mapping;

import org.springframework.web.bind.annotation.RequestMethod;

public class RequestInfo {
    private String controllerName;
    private Class<?> controllerType;
    private RequestMethod requestMethod;
    private String pageLifePhase;
    private String methodName;

    public RequestInfo() {
        super();
    }


    public RequestInfo(String controllerName, Class<?> controllerType, RequestMethod requestMethod,
                       String pageLifePhase, String methodName) {
        super();
        this.controllerName = controllerName;
        this.controllerType = controllerType;
        this.requestMethod = requestMethod;
        this.pageLifePhase = pageLifePhase;
        this.methodName = methodName;
    }


    public String getControllerName() {
        return controllerName;
    }


    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }


    public RequestMethod getRequestMethod() {
        return requestMethod;
    }


    public void setRequestMethod(RequestMethod requestMethod) {
        this.requestMethod = requestMethod;
    }


    public String getPageLifePhase() {
        return pageLifePhase;
    }


    public void setPageLifePhase(String pageLifePhase) {
        this.pageLifePhase = pageLifePhase;
    }


    /**
     * @return the controllerType
     */
    public Class<?> getControllerType() {
        return controllerType;
    }


    /**
     * @param controllerType the controllerType to set
     */
    public void setControllerType(Class<?> controllerType) {
        this.controllerType = controllerType;
    }


    public String getMethodName() {
        return methodName;
    }


    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }


    @Override
    public String toString() {
        return "RequestInfo...controllerName:" + this.controllerName + ";controllerType:" + this.controllerType + ";requestMethod:" + this.requestMethod + ";pageLifePhase:" + pageLifePhase + ";methodName:" + this.methodName;
    }

}
