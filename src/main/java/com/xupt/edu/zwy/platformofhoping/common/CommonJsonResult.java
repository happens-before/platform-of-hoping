package com.xupt.edu.zwy.platformofhoping.common;

import com.xupt.edu.zwy.platformofhoping.enums.ReturnCodes;
import lombok.Data;

import java.util.Objects;

/**
 * Created with IntelliJ IDEA
 * Description:
 *
 * @author wanyuezhao
 * @Date 19-3-14
 * @Time 上午8:14
 */
@Data
public final class CommonJsonResult<T> {

    private ReturnCodes returnCodes;

    private String message;

    private T data;

    private static final CommonJsonResult<Void> SUCCESS_RESULT = new CommonJsonResult<Void>()
            .setReturnCodes(ReturnCodes.OK).setMessage("操作成功");

    private CommonJsonResult() {
    }

    public static CommonJsonResult<Void> success() {
        return SUCCESS_RESULT;
    }

    public static <T> CommonJsonResult<T> success(T data) {
        Objects.requireNonNull(data);
        return new CommonJsonResult<T>().setReturnCodes(ReturnCodes.OK).setMessage("操作成功").setData(data);
    }

    public static CommonJsonResult<Void> fail(ReturnCodes httpStatus, String message) {
        Objects.requireNonNull(httpStatus);
        return new CommonJsonResult<Void>().setReturnCodes(httpStatus).setMessage(message);
    }

    public ReturnCodes getReturnCodes() {
        return returnCodes;
    }

    public CommonJsonResult<T> setReturnCodes(ReturnCodes returnCodes) {
        this.returnCodes = returnCodes;
        return this;
    }

    public T getData() {
        return data;
    }

    public CommonJsonResult<T> setData(T data) {
        this.data = data;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public CommonJsonResult<T> setMessage(String message) {
        this.message = message;
        return this;
    }
}