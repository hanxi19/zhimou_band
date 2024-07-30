package com.zhimou.zhimou_service.controller;

import com.zhimou.zhimou_service.myUtil.Result;
import com.zhimou.zhimou_service.pojo.help_seek.BodyData;
import com.zhimou.zhimou_service.pojo.help_seek.HelpSeek;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MedicalController {
    @GetMapping("/get_body_data")
    public Result getBodyData(){
        return Result.success(new BodyData(HelpSeek.getBloodOxygen(),HelpSeek.getHeartRate()));
    }
}
