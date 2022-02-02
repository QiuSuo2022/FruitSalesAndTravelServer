package com.guet.qiusuo.fruittravel.config;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice(basePackages = "com.guet")
public class ResponseWrappingConfig implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // 不是json则不嵌套
        if (!(MediaType.APPLICATION_JSON.equals(selectedContentType))) {
            return body;
        }
        if (!(body instanceof Response)) {
            Response<Object> r = new Response<>();
            r.setCode(0);
            r.setMsg("OK");
            r.setData(body);
            return r;
        }

        return body;
    }

}
