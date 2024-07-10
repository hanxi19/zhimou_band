package com.zhimou.zhimou_service.service.VolunteerPlatformImpl;

import com.zhimou.zhimou_service.mapper.VolunteerPlatformMapper;
import com.zhimou.zhimou_service.service.VolunteerPlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VolunteerPlatformService1 implements VolunteerPlatformService {
    @Autowired
    private VolunteerPlatformMapper mapper;
    @Override
    public Integer getPoint() {
        return mapper.getPoint();
    }
    public void addPoint(Integer point){
        mapper.addPoint(point);
    }
    public void insertPoint(){
        mapper.insertPoint();
    }
}
