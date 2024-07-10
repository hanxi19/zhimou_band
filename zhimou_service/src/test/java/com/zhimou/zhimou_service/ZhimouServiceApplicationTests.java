package com.zhimou.zhimou_service;

import com.zhimou.zhimou_service.controller.VolunteerPlatformController;
import com.zhimou.zhimou_service.mapper.VolunteerPlatformMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ZhimouServiceApplicationTests {
    @Autowired
    private VolunteerPlatformMapper mapper;
    @Test
    void contextLoads() {
    }

}
