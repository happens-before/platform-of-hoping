package com.xupt.edu.zwy.platformofhoping.enums;

/**
 * Created with IntelliJ IDEA
 * Description:
 *
 * @author wanyuezhao
 * @Date 19-3-14
 * @Time 下午2:23
 */
public enum ReturnCodes {

    ILLEGAL_ARGUMENT(100000),
    OK(200000),
    FAILD(300000),
    NULL_POINTER(600000);
    private int code;

    ReturnCodes(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}