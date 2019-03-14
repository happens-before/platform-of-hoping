package com.xupt.edu.zwy.platformofhoping.common;


import com.xupt.edu.zwy.platformofhoping.enums.ReturnCodes;

/**
 * Created with IntelliJ IDEA
 * Description:
 *
 * @author wanyuezhao
 * @Date 19-3-14
 * @Time 下午3:57
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private ReturnCodes errorCode;

    public BusinessException(ReturnCodes errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ReturnCodes getErrorCode() {
        return errorCode;
    }
}
