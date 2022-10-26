package org.hulei.reggie.common;

import org.apache.logging.log4j.message.Message;

/**
 * @author Mr.Hu
 * @create 2022/10/24 9:09
 */
public class CustomException extends RuntimeException{
    public CustomException(String msg){
        super(msg);
    }
}
