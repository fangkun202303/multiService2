package com.moduls.dubboprovider.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InsertException extends Exception{

    private Logger logger = LoggerFactory.getLogger(InsertException.class);

    public InsertException (){
        super();
    }

    public InsertException (String message){
        super(message);
        System.out.println("出错了！");
        logger.debug(message);
    }
}
