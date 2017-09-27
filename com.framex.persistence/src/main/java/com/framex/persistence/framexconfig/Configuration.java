package com.framex.persistence.framexconfig;

import java.util.Map;
import java.util.Set;

/**
 * @author lijie
 * @date 2017/9/27 11:50
 * @description
 */
public class Configuration {
    private Map<String, String> zookeeper;
    private Set<Map<String, String>> apiGateWay;
    private FramexModule module;
    private Set<String> requiredModules;


    public Map<String, String> getZookeeper() {
        return zookeeper;
    }

    public void setZookeeper(Map<String, String> zookeeper) {
        this.zookeeper = zookeeper;
    }

    public Set<Map<String, String>> getApiGateWay() {
        return apiGateWay;
    }

    public void setApiGateWay(Set<Map<String, String>> apiGateWay) {
        this.apiGateWay = apiGateWay;
    }

    public FramexModule getModule() {
        return module;
    }

    public void setModule(FramexModule module) {
        this.module = module;
    }

    public Set<String> getRequiredModules() {
        return requiredModules;
    }

    public void setRequiredModules(Set<String> requiredModules) {
        this.requiredModules = requiredModules;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "zookeeper=" + zookeeper +
                ", apiGateWay=" + apiGateWay +
                ", module=" + module +
                ", requiredModules=" + requiredModules +
                '}';
    }


}
