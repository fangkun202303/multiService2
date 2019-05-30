package com.moduls.dubboprovider.config;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Description: 对错误返回码的数据进行自适应
 *
 * @author fangkun
 * @createDate 2019/5/21 15:14
 **/
@Component
public class MyErrorController extends DefaultErrorAttributes {

    /**
     * 重写org.springframework.boot.web.servlet.error.DefaultErrorAttributes类中的getErrorAttributes，将
     * 我们自定义的错误信息添加进去
     * @param webRequest
     * @param includeStackTrace
     * @return
     */
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest,
                                                  boolean includeStackTrace) {
        Map<String, Object> errorAttributes = new LinkedHashMap<>();
        errorAttributes.put("customCode",1234);
        errorAttributes.put("customMsg",webRequest.getAttribute("customMsg",0));
        return errorAttributes;
    }
}
