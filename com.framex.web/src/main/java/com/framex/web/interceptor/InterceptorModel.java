package com.framex.web.interceptor;

public class InterceptorModel {
    private int interceptOrder;
    private Interceptor interceptor;
    private TargetControllerDefinition targetControllers;
    private TargetMethodDefinition targetMethodDefinition;

    public InterceptorModel(Interceptor interceptor) throws Exception {
        this.interceptor = interceptor;
        this.interceptOrder = interceptor.setInterceptOrder();
        this.targetControllers = interceptor.setTargetController();
        this.targetMethodDefinition = interceptor.setTargetMethod();
    }

    public int getInterceptOrder() {
        return interceptOrder;
    }

    public void setInterceptOrder(int interceptOrder) {
        this.interceptOrder = interceptOrder;
    }

    public Interceptor getInterceptor() {
        return interceptor;
    }

    public void setInterceptor(Interceptor interceptor) {
        this.interceptor = interceptor;
    }

    public TargetControllerDefinition getTargetControllers() {
        return targetControllers;
    }

    public void setTargetControllers(TargetControllerDefinition targetControllers) {
        this.targetControllers = targetControllers;
    }

    public TargetMethodDefinition getTargetMethodDefinition() {
        return targetMethodDefinition;
    }

    public void setTargetMethodDefinition(TargetMethodDefinition targetMethodDefinition) {
        this.targetMethodDefinition = targetMethodDefinition;
    }

}
