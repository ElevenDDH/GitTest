package com.sise.service01.feign;

import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import feign.gson.GsonDecoder;

import java.io.IOException;
import java.lang.reflect.Type;

public class MyDecoder implements Decoder {
    private GsonDecoder gsonDecoder;

    public MyDecoder(){
        gsonDecoder = new GsonDecoder();
    }

    @Override
    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
        System.out.println("自定义解码器");
        return gsonDecoder.decode(response, type);
    }
}
