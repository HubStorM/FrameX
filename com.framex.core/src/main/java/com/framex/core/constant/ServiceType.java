package com.framex.core.constant;

public enum ServiceType {
    ONE2ONE_SYNC("ONE2ONE_SYNC", 1),
    ONE2ONE_ASYNC_NOTIFICATION("ONE2ONE_ASYNC_NOTIFICATION", 2),
    ONE2ONE_ASYNC_RESPONSE("ONE2ONE_ASYNC_RESPONSE", 3),
    ONE2MANY_ASYNC_PUBLISH_SUBSCRIBE("ONE2MANY_ASYNC_PUBLISH_SUBSCRIBE", 4),
    ONE2MANY_ASYNC_REQUEST_RESPONSE("ONE2MANY_ASYNC_REQUEST_RESPONSE", 5);

    private String name;
    private int index;

    private ServiceType(String name, int index){
        this.name = name;
        this.index = index;
    }

}
