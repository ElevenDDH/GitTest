package com.sise.service01.feign;

import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import feign.gson.GsonEncoder;

import java.lang.reflect.Type;

public class MyEncoder implements Encoder {
    private GsonEncoder gsonEncoder;

    public MyEncoder(){
        gsonEncoder = new GsonEncoder();
    }

    @Override
    public void encode(Object o, Type type, RequestTemplate requestTemplate) throws EncodeException {
        System.out.println("自定义编码器");
        System.out.println("encode object is class " + o.getClass().getName());
        System.out.println("encode object is value " + o);
        System.out.println("encode Type is class " + type.getClass().getName());
        System.out.println("encode Type is value " + type);

        gsonEncoder.encode(o, type, requestTemplate);
    }
}
