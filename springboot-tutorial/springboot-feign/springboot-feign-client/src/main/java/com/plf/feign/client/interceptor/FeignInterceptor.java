package com.plf.feign.client.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

/**
 * @author panlf
 * @date 2022/5/9
 */
@Component
public class FeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        System.out.println("requestTemplate url ====> " + requestTemplate.feignTarget().url());
        String name = "token";
        String value = "client-token-123456";
        //同理可以放入Cookie
        requestTemplate.header(name, value);
        //传递Header
        /*RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            if (request != null) {
                //设置header属性：requestTemplate.header(name, values);
                Enumeration<String> headerNames = request.getHeaderNames();
                if (headerNames != null) {
                    while (headerNames.hasMoreElements()) {
                        String name = headerNames.nextElement();
                        String values = request.getHeader(name);
                        requestTemplate.header(name, values);
                    }
                }

                //设置param属性：requestTemplate.query(name, values);
                Enumeration<String> paramNames = request.getParameterNames();
                if (paramNames != null) {
                    Map map=new HashMap();
                    while (paramNames.hasMoreElements()) {
                        String name = paramNames.nextElement();
                        String values = request.getParameter(name);
                        requestTemplate.query(name, values);
                    }
                }
            }
        }*/
    }
}
