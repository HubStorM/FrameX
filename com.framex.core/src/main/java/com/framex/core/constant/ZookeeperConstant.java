package com.framex.core.constant;

public enum ZookeeperConstant {
    SERVICE_LOCK("/service_lock"), SERVICE_ROOT("/service");

    private String value;

    private ZookeeperConstant(String value) {
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }


    @Override
    public String toString() {
        return this.value;
    }
}
