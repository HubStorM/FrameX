package com.framex.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @version [版本号, 2017年7月31日]
 * @作者 undefined
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface Interceptor {
    /**
     * 设置拦截器的拦截顺序
     * Interceptor1 --> Interceptor2 --> Controller method --> Interceptor3
     * ordere   2            --> 1            --> 0                 --> -1
     *
     * @return
     * @exception/throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    int setInterceptOrder();

    /**
     * 设置拦截器需要应用到的controller列表。
     *
     * @return controller类列表
     * @exception/throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    TargetControllerDefinition setTargetController();

    /**
     * 设置拦截器需要应用到的controller中的方法
     *
     * @return
     * @exception/throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    TargetMethodDefinition setTargetMethod();

    /**
     * 拦截方法。
     *
     * @param input    上一个拦截器的返回值。
     * @param request
     * @param response
     * @param session
     * @return O 该拦截器返回的值，需要在实现接口时指定泛型。
     * @exception/throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    Map<Object, Object> intercept(Map<Object, Object> input, HttpServletRequest request, HttpServletResponse response, HttpSession session);
}
