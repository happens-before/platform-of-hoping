package com.xupt.edu.zwy.platformofhoping.dao;

import com.xupt.edu.zwy.platformofhoping.dto.NewsAddReq;
import com.xupt.edu.zwy.platformofhoping.dto.NewsDto;
import com.xupt.edu.zwy.platformofhoping.dto.NewsHomeDto;
import com.xupt.edu.zwy.platformofhoping.dto.NewsInfoDto;
import com.xupt.edu.zwy.platformofhoping.model.Picture;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wanyuezhao
 */
@Mapper
public interface IPictureDao {
    List<NewsHomeDto> getNewsHomeInfo();

    int addPicture(NewsAddReq newsAddReq);

    int updatePicture(NewsAddReq newsAddReq);

    List<Picture> selectPictureByNewsId(@Param("newsId") String newsId);

    int deletePictureById(@Param("newsId") String newsId);
}
