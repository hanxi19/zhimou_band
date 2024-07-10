package com.zhimou.zhimou_service.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VolunteerPlatformMapper {
    public Integer getPoint();
    public void addPoint(Integer point);
    public void insertPoint();
}
