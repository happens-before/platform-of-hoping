package com.xupt.edu.zwy.platformofhoping.dao;

import com.xupt.edu.zwy.platformofhoping.dto.NewsDto;
import com.xupt.edu.zwy.platformofhoping.dto.NewsHomeDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IPictureDao {
    List<NewsHomeDto> getNewsHomeInfo(List<NewsDto> newsDtos);
}
