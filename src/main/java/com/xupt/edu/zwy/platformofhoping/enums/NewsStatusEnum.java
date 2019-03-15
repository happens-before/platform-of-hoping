package com.xupt.edu.zwy.platformofhoping.enums;

/**
 * Created with IntelliJ IDEA
 * Description:
 *
 * @author wanyuezhao
 * @Date 19-3-14
 * @Time 下午7:48
 */
public enum NewsStatusEnum {

    UN_PUBLISH(0, "未发布"),
    PUBLISHED(10, "已发布"),
    PAUSE(20, "暂停"),
    ALL_STATUS(30, "全部");

    private int code;
    private String status;

    NewsStatusEnum(int code, String status) {
        this.code = code;
        this.status = status;
    }

    public static NewsStatusEnum valueOf(int type) {
        for (NewsStatusEnum newsStatusEnum : NewsStatusEnum.values()) {
            if (newsStatusEnum.getCode() == type) {
                return newsStatusEnum;
            }
        }
        throw new IllegalArgumentException("conversion failed.");
    }

    public int getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
