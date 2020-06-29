package com.sise.gateway;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

public class RestTemplateFilter extends ZuulFilter {
    private RestTemplate restTemplate;

    public RestTemplateFilter(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
    @Override
    public String filterType() {
        return FilterConstants.ROUTE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        //获取请求url
        String uri = request.getRequestURI();
        //为了不影响其他路由，uri中含有rest-tpl-sale才执行本路由器
        return uri.contains("rest-tpl-sale");
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();

        //调用服务id
        String serviceId = (String)context.get("serviceId");
        //请求id
        String uri = (String)context.get("requestURI");
        //组合url给RestTemplate调用
        String url = "http://" + serviceId + uri;
        System.out.println("执行RestTemplateFilter调用得url：" + url);
        //调用并获取结果
        String result = this.restTemplate.getForObject(url, String.class);
        //设置路由状态，表示已经进行 路由
        context.setResponseBody(result);
        //设置响应标识
        context.sendZuulResponse();
        return null;
    }
}
