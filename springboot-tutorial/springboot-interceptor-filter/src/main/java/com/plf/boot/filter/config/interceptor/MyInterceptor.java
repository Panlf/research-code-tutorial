package com.plf.tutorial.boot.filter.config.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器
 *   基于Java的反射机制（动态代理）实现的
 *   需要注册之后才能生效
 */
//值越小级别越高越先执行
@Order(Ordered.HIGHEST_PRECEDENCE) // 在这里定义不起作用
@Component
@Slf4j
public class MyInterceptor implements HandlerInterceptor {

    /**
     * 这个方法将在请求处理之前进行调用。
     * 注意：如果该方法的返回值为false ，将视为当前请求结束，不仅自身的拦截器会失效，还会导致其他的拦截器也不再执行。
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("MyInterceptor.preHandle");

        return true;
    }

    /**
     * 只有在 preHandle()方法返回值为true时才会执行。会在Controller中的方法调用之后，DispatcherServlet返回渲染视图之前被调用。
     *  postHandle()方法被调用的顺序跟 preHandle()是相反的，先声明的拦截器 preHandle() 方法先执行，而postHandle()方法反而会后执行。
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("MyInterceptor.postHandle");
    }

    /**
     * 只有在 preHandle() 方法返回值为true 时才会执行。
     * 在整个请求结束之后， DispatcherServlet 渲染了对应的视图之后执行。
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("MyInterceptor.afterCompletion");
    }
}
