package com.xupt.edu.zwy.platformofhoping.dao;

import com.xupt.edu.zwy.platformofhoping.dto.UserLoginReq;
import com.xupt.edu.zwy.platformofhoping.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created with IntelliJ IDEA
 * Description:
 *
 * @author wanyuezhao
 * @Date 19-3-16
 * @Time 下午9:03
 */
@Mapper
public interface IUserDao {
    int addUser(User user);

    User isRightInfo(UserLoginReq userLoginReq);

    User isUser(@Param("userName") String userName);

    User selectUserByUserId(@Param("userId") String userId);
}
