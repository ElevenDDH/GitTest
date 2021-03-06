package com.sise.gateway;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.cloud.netflix.zuul.util.ZuulRuntimeException;

import javax.servlet.http.HttpServletRequest;

public class ExceptionFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.ROUTE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 3;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        //获取uri
        String uri = request.getRequestURI();
        //含有exceptionTest才执行过滤器
        return uri.contains("exceptionTest");
    }

    @Override
    public Object run() {
        System.out.println("执行ExceptionFilter，抛出异常");
        throw new ZuulRuntimeException(
                new ZuulException("exception msg", 201, "my cause"));
    }
}
