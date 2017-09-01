package com.framex.core.constant;

import java.util.Objects;

public abstract class AbstractService {
    protected String serviceName;
    protected String serviceGuid;
    protected String serviceVersion;
    protected String serviceEdition;
    protected String group;
    protected String module;

    public abstract ServiceType getSelfType();


    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceGuid() {
        return serviceGuid;
    }

    public void setServiceGuid(String serviceGuid) {
        this.serviceGuid = serviceGuid;
    }

    public String getServiceVersion() {
        return serviceVersion;
    }

    public void setServiceVersion(String serviceVersion) {
        this.serviceVersion = serviceVersion;
    }

    public String getServiceEdition() {
        return serviceEdition;
    }

    public void setServiceEdition(String serviceEdition) {
        this.serviceEdition = serviceEdition;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }
}
