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
    TIME_CHECK_FAILURE(400000),
    QTALK_CHECK_FAILURE(500000),
    NULL_POINTER(600000),
    NOT_REALPERSON(700000),
    NOT_LOGIN(800000),
    BLANK(900000),
    OUT_OF_LENGTH(1000000),
    UNEXPECT_FAILED(110000),
    EXCLE_DOWNLOAD_FAILED(120000);
    private int code;

    ReturnCodes(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}