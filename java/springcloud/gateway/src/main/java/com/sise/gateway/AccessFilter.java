package com.sise.gateway;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletRequest;

/**
 * 定义一个Zuul过滤器，实现在请求被路由之前检查请求中是否有accessToken参数，
 * 若有就进行路由，若没有就拒绝访问，返回401 with no accessToken错误
 */
public class AccessFilter extends ZuulFilter {
    /**
     * 返回字符串代表过滤器类型
     * pre：路由之前
     * routing：路由之时
     * post：路由之后
     * error：发送错误调用
     */

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }
    //  逻辑判断是否要过滤
    @Override
    public boolean shouldFilter() {
        return true;//永远过滤
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        Object accessToken = request.getParameter("accessToken");
        if (accessToken == null) {
            System.out.println("access token is empty");

            return "access is Null";
        }
        System.out.println("access token ok");
        return null;
    }
}
