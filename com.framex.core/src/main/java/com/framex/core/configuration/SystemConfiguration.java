package com.framex.core.configuration;

import java.util.List;
import java.util.Map;

/**
 * @author lijie
 * @date 2017/9/25 11:01
 * @description
 */
public interface SystemConfiguration {
    String getModuleName();
    String getModuleUri();
    String getModuleIp();
    String getModulePort();

    String getStringValue(String key);
    <T> T getObject(String key, Class<T> requiredType);
    Map<String, Object> getObject(String key);
    <T> List<T> getList(String key, Class<T> elementType);
    List<Map<String, Object>> getList(String key);

}
