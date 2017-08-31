package com.framex.web.interceptor;

import java.util.List;

public class TargetControllerDefinition {
    private List<Class<?>> targetControllers;
    private boolean interceptAllControllers;

    public TargetControllerDefinition() {
        super();
    }

    public TargetControllerDefinition(List<Class<?>> targetControllers, boolean interceptAllControllers) {
        super();
        this.targetControllers = targetControllers;
        this.interceptAllControllers = interceptAllControllers;
    }

    public List<Class<?>> getTargetControllers() {
        return targetControllers;
    }

    public void setTargetControllers(List<Class<?>> targetControllers) {
        this.targetControllers = targetControllers;
    }

    public boolean isInterceptAllControllers() {
        return interceptAllControllers;
    }

    public void setInterceptAllControllers(boolean interceptAllControllers) {
        this.interceptAllControllers = interceptAllControllers;
    }


}
