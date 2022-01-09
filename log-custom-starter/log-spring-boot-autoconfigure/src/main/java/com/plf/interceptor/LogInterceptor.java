package com.plf.interceptor;

import com.plf.annotation.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author panlf
 * @date 2021/12/31
 */
public class LogInterceptor implements HandlerInterceptor {

    private static final ThreadLocal<Long> threadLocal = new ThreadLocal<>();


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       // if(handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Log methodAnnotation = handlerMethod.getMethodAnnotation(Log.class);
            if (methodAnnotation != null) {
                long start = System.currentTimeMillis();
                threadLocal.set(start);
            }
      //  }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {
        //if(handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            Log methodAnnotation = handlerMethod.getMethodAnnotation(Log.class);
            if (methodAnnotation != null) {
                String requestUri = request.getRequestURI();
                String globalMethodName = method.getDeclaringClass().getName() + "#" + method.getName();
                String desc = methodAnnotation.desc();
                long end = System.currentTimeMillis();
                long start = threadLocal.get();
                long during = end - start;
                threadLocal.remove();
                System.out.println(String.format("请求路径 : %s,请求方法 : %s,描述信息 : %s,总计耗时 : %d", requestUri, globalMethodName, desc, during));
            }
      //  }
    }
}
