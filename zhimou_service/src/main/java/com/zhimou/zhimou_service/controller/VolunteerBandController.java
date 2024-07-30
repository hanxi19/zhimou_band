package com.zhimou.zhimou_service.controller;

import com.zhimou.zhimou_service.myUtil.Result;
import com.zhimou.zhimou_service.pojo.help_seek.HelpSeek;
import com.zhimou.zhimou_service.service.VolunteerPlatformService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
public class VolunteerBandController {
    @Autowired
    private VolunteerPlatformService service;
    @GetMapping("/navi_finished")
    public Result naviFinished(){
        HelpSeek.arrive();
        log.info("navifinished");
        return Result.success(null);
    }
    @GetMapping("/help_finished")
    public Result helpFinished(boolean finished){
        if(finished){
            if(service.getPoint()==null){
                service.insertPoint();
            }
            service.addPoint(1+service.getPoint());
            HelpSeek.finish();
            log.info("help finished");
        }else{
            HelpSeek.notFinish();
            log.info("help not finish");
        }
        return Result.success(null);
    }
}
