package com.moduls.dubboprovider.config;

import org.jboss.netty.handler.codec.http.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局处理异常
 */
@ControllerAdvice
public class MyExceptionConfig {

    private Logger logger = LoggerFactory.getLogger(MyExceptionConfig.class);

    /**
     * 返回的全是json数据
     * @param e
     * @return
     */
//    @ResponseBody
//    @ExceptionHandler(Exception.class)
//    public Map<String,Object> exceptionHandler(Exception e){
//        Map<String,Object> map=new HashMap<>();
//
//        map.put("code",1234);
//        map.put("customMsg",e.getMessage());
//
//        return map;
//    }

    /**
     * 异常自适应
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public String exceptionHandler(Exception e, HttpServletRequest request){
        /**
         * 在包的org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController下有设置状态码的
         * 		Integer statusCode = (Integer) request
         * 				.getAttribute("javax.servlet.error.status_code");
         */
        // 可以将这些异常的信息放入request域中，我们可以在自定义MyErrorController中的webRequest对象取出
        request.setAttribute("customMsg",e.getMessage());
        logger.info("======>>>>>>>>>>>"+e.getLocalizedMessage());
        return "forward:/error";
    }
}
