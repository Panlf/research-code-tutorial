package com.plf.boot.unified.interceptor;

import org.apache.logging.log4j.ThreadContext;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author panlf
 * @date 2021/9/2
 */
@Component
public class TranceIdInterceptor implements HandlerInterceptor {
    private final static String TRACE_ID = "traceId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String traceId = java.util.UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        ThreadContext.put(TRACE_ID, traceId);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        ThreadContext. remove(TRACE_ID);
    }
}
