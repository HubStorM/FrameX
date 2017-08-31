package com.framex.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;


/**
 * 视图(html) --> controller属性映射拦截
 *
 * @version [版本号, 2017年8月2日]
 * @作者 undefined
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class FieldMappingInterceptor implements Interceptor {

    @Override
    public int setInterceptOrder() {
        return 1;
    }

    @Override
    public TargetControllerDefinition setTargetController() {
        return new TargetControllerDefinition(null, true);
    }

    @Override
    public TargetMethodDefinition setTargetMethod() {
        return new TargetMethodDefinition(null, null, true, true, false);
    }

    @Override
    public Map<Object, Object> intercept(Map<Object, Object> input, HttpServletRequest request, HttpServletResponse response,
                                         HttpSession session) {
        return null;
    }

}
