package com.framex.core.configuration.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.framex.core.configuration.SystemConfiguration;

import java.util.List;
import java.util.Map;

/**
 * @author lijie
 * @date 2017/9/25 11:48
 * @description
 */
public class YamlConfiguration implements SystemConfiguration{

    private static final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());


    @Override
    public String getModuleName() {
        return null;
    }

    @Override
    public String getModuleUri() {
        return null;
    }

    @Override
    public String getModuleIp() {
        return null;
    }

    @Override
    public String getModulePort() {
        return null;
    }

    @Override
    public String getStringValue(String key) {
        return null;
    }

    @Override
    public <T> T getObject(String key, Class<T> requiredType) {
        return null;
    }

    @Override
    public Map<String, Object> getObject(String key) {
        return null;
    }

    @Override
    public <T> List<T> getList(String key, Class<T> elementType) {
        return null;
    }

    @Override
    public List<Map<String, Object>> getList(String key) {
        return null;
    }
}
