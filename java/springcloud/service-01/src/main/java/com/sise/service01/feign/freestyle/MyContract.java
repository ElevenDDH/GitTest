package com.sise.service01.feign.freestyle;

import feign.Contract;
import feign.MethodMetadata;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

//自定义翻译器
public class MyContract extends Contract.BaseContract {
    @Override
    protected void processAnnotationOnClass(MethodMetadata methodMetadata, Class<?> aClass) {
        //处理类注解
    }

    @Override
    protected void processAnnotationOnMethod(MethodMetadata methodMetadata, Annotation annotation, Method method) {
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

    @Override
    protected boolean processAnnotationsOnParameter(MethodMetadata methodMetadata, Annotation[] annotations, int i) {
        //处理参数注解
        return false;
    }
}
