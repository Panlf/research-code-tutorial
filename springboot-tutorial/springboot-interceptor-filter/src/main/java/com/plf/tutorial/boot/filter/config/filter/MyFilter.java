package com.plf.tutorial.boot.filter.config.filter;

import com.plf.tutorial.boot.filter.utils.IPUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 自定义拦截器
 * 基于函数回调
 */
//@Component
@Slf4j
public class MyFilter implements Filter {

    /**
     * 该方法在容器启动初始化过滤器时被调用，它在 Filter 的整个生命周期只会被调用一次。
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("MyFilter.init");
    }

    /**
     * 容器中的每一次请求都会调用该方法， FilterChain 用来调用下一个过滤器 Filter。
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("MyFilter.doFilter");
        /**
         * 过滤逻辑
         */
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String uri = request.getRequestURI();

        String ip = IPUtil.getIpAddr(request);

        log.info("获取当前的Session:{}",session);
        log.info("获取访问的当前的URI:{}",uri);
        log.info("获取访问的当前的IP:{}",ip);


        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     *  当容器销毁 过滤器实例时调用该方法，一般在方法中销毁或关闭资源，在过滤器 Filter 的整个生命周期也只会被调用一次
     */
    @Override
    public void destroy() {
        log.info("MyFilter.destroy");
    }
}
