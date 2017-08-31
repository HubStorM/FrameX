package com.framex.web.handler;

import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 请求处理类。借助于springmvc的dispatcherservlet的请求分发功能，
 * 处理由HandlerMapping的实现类解析获得的处理对象handler，
 * 主要作用是反射调用Controller中的处理函数以及调用handler中定义的拦截器。
 *
 * @version [版本号, 2017年7月30日]
 * @作者 undefined
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class FrameXHandlerAdapter implements HandlerAdapter {


    @Override
    public boolean supports(Object handler) {
        return handler instanceof InvokeInfo;
    }


    /**
     * 调用requestMapping生成的InvokeInfo对象：
     * 1、完成被调用方法的参数注入
     * 2、完成拦截器的调用
     * 3、完成目标方法的执行
     */
    @Override
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        return null;
    }

    @Override
    public long getLastModified(HttpServletRequest request, Object handler) {
        return 0;
    }

}
