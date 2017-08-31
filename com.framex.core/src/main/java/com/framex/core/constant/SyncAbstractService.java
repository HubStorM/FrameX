package com.framex.core.constant;

public abstract class SyncAbstractService extends AbstractService{
    protected String uri;
    protected String host;
    protected String port;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
