package com.framex.web.mapping;

import com.framex.web.annotation.pagephase.PageLifeAnnotationUtil;
import com.framex.web.handler.InvokeInfo;
import com.framex.web.interceptor.Interceptor;
import com.framex.web.interceptor.InterceptorModel;
import com.framex.web.util.ApplicationContextUtil;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 请求分析类。主要功能是:
 * 注册Controller中的方法;
 * 由springmvc的dispatcherservlet将请求分发至getHandler方法，根据已经注册的Controller方法匹配Url
 * 获得InvokeInfo，由springmvc调度，发送到HandlerAdapter处理。
 *
 * @version [版本号, 2017年7月30日]
 * @作者 undefined
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class FrameXHandlerMapping implements HandlerMapping, InitializingBean {

    /**
     * 存放处理方法的map，key-value --> RequestInfo(用于匹配请求的信息)-InvokeInfo(处理的方法和拦截器)
     */
    private static final Map<RequestInfo, InvokeInfo> handlerMap = new HashMap<RequestInfo, InvokeInfo>();

    /**
     * 默认参数注入实现类
     */

    /**
     * 默认拦截器列表
     */
    private static final List<InterceptorModel> interceptors = new ArrayList<InterceptorModel>();


    /**
     * 请求分发的入口，返回HandlerExecutionChain，用于HandlerAdapter反射执行。
     */
    @Override
    public HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception {
        //TODO 变量验证
        String[] servletPath = request.getServletPath().split("/");
        for (RequestInfo item : handlerMap.keySet()) {
            if (servletPath[1].equals(item.getControllerName()) && servletPath[2].equals(item.getMethodName())) {
                HandlerExecutionChain chain = new HandlerExecutionChain(handlerMap.get(item));
                return chain;
            }
        }
        return null;
    }


    /**
     * 实现InitializingBean接口，在init-method执行之后回调该方法。
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        //扫描拦截器
        initInterceptors();
        //扫描controller注解，初始化handlerMap
        initHandlerMap();
    }

    private void initInterceptors() {
        String[] controllerNames = BeanFactoryUtils.beanNamesForTypeIncludingAncestors(ApplicationContextUtil.getContext(), Object.class);
        for (String item : controllerNames) {
            Class<?> itemType = ApplicationContextUtil.getContext().getType(item);
            //注册拦截器
            if (Arrays.asList(itemType.getInterfaces()).contains(Interceptor.class)) {
                try {
                    InterceptorModel interceptorModel = new InterceptorModel((Interceptor) itemType.newInstance());
                    interceptors.add(interceptorModel);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void initHandlerMap() {
        String[] controllerNames = BeanFactoryUtils.beanNamesForTypeIncludingAncestors(ApplicationContextUtil.getContext(), Object.class);
        for (String item : controllerNames) {
            //注册Controller
            if (ApplicationContextUtil.getContext().findAnnotationOnBean(item, Controller.class) != null) {
                Class<?> controllerType = ApplicationContextUtil.getContext().getType(item);
                Method[] methods = controllerType.getDeclaredMethods();
                //处理controller中的方法
                for (Method method : methods) {
                    String methodName = method.getName();
                    //注册生命周期方法
                    if (isPageLifePhaseMethod(method)) {
                        registerPageLifePhaseMethod(controllerType, method);
                    }
                    //注册restful方法
                    if (isRestfulMethod(methodName)) {
                        registerRestfulMethod(controllerType, method);
                    }
                    //TODO 其他方法
                }
            }
        }
    }


    @SuppressWarnings("unchecked")
    private boolean isPageLifePhaseMethod(Method method) {
        List<Class<?>> pageLifeAnnotations = PageLifeAnnotationUtil.getAllPageLifeAnnotations();
        for (Class<?> item : pageLifeAnnotations) {
            if (method.isAnnotationPresent((Class<? extends Annotation>) item)) {
                return true;
            }
        }
        return false;
    }

    private void registerPageLifePhaseMethod(Class<?> controllerType, Method method) {
        String pageLifePhase = null;
        Annotation[] annotations = method.getDeclaredAnnotations();
        List<Class<?>> pageLifeAnnotations = PageLifeAnnotationUtil.getAllPageLifeAnnotations();
        for (Annotation item : annotations) {
            if (pageLifeAnnotations.contains(item.getClass())) {
                pageLifePhase = item.getClass().getSimpleName();
            }
        }
        RequestInfo key = new RequestInfo(controllerType.getSimpleName(), controllerType, RequestMethod.GET, pageLifePhase, method.getName());
        InvokeInfo value = new InvokeInfo(method, interceptors);
        handlerMap.put(key, value);
    }

    private void registerRestfulMethod(Class<?> controllerType, Method method) {
        String methodName = method.getName();
        RequestInfo key = new RequestInfo();
        key.setControllerName(controllerType.getSimpleName());
        key.setControllerType(controllerType);
        key.setMethodName(methodName);
        if (methodName.startsWith("get")) {
            key.setRequestMethod(RequestMethod.GET);
        }
        if (methodName.startsWith("post")) {
            key.setRequestMethod(RequestMethod.POST);
        }
        if (methodName.startsWith("put")) {
            key.setRequestMethod(RequestMethod.PUT);
        }
        if (methodName.startsWith("delete")) {
            key.setRequestMethod(RequestMethod.DELETE);
        }
        InvokeInfo value = new InvokeInfo(method, interceptors);
        handlerMap.put(key, value);
    }

    private boolean isRestfulMethod(String methodName) {
        return (methodName.startsWith("get") || methodName.startsWith("put")
                || methodName.startsWith("delete") || methodName.startsWith("post"));
    }

    public static Map<RequestInfo, InvokeInfo> getHandlermap() {
        return handlerMap;
    }

    public static List<InterceptorModel> getInterceptors() {
        return interceptors;
    }


}
