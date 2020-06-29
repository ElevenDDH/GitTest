package com.sise.service02.feign;

import feign.MethodMetadata;
import org.springframework.cloud.openfeign.support.SpringMvcContract;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

//自定义翻译器
public class MyContract extends SpringMvcContract {
    @SuppressWarnings("deprecation")
    protected void processAnnotationOnMethod(MethodMetadata methodMetadata, Annotation annotation, Method method) {
        //调用父类的方法，可以支持@RequestMapping注解
        super.processAnnotationOnMethod(methodMetadata, annotation, method);

        //用于处理方法的注解
        //判断是否是MyUrl注解
        if (MyUrl.class.isInstance(annotation)){
            //获取注解实例
            MyUrl myUrl = method.getAnnotation(MyUrl.class);
            //获取注解配置的Http方法
            String httpMethod = myUrl.method();
            //获取注解配置的Url
            String url = myUrl.url();
            //将MyUrl的url、method属性分别设置到feign的模板中
            methodMetadata.template().method(httpMethod);
            methodMetadata.template().append(url);
        }
    }

}
