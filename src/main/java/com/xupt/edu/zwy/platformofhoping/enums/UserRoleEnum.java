package com.xupt.edu.zwy.platformofhoping.enums;

/**
 * Created with IntelliJ IDEA
 * Description:
 *
 * @author wanyuezhao
 * @Date 19-3-16
 * @Time 下午9:34
 */
public enum UserRoleEnum {
    ADMIN("管理员", 3),
    USER("用户", 2),
    ORGANIZER("组织", 1);


    private String role;

    private int roleFlag;

    UserRoleEnum(String role, int roleFlag) {
        this.role = role;
        this.roleFlag = roleFlag;
    }

    public String getRole() {
        return this.role;
    }

    public int getRoleFlag() {
        return this.roleFlag;
    }
}
