package com.framex.soa;

public class ServiceHolder {
    private static class ServiceLoader{
        private static final ServiceHolder INSTANCE = new ServiceHolder();
    }

    public static ServiceHolder getInstance() {
        return ServiceLoader.INSTANCE;
    }

    private ServiceHolder() {

    }
}
