package com.zhimou.zhimou_service.controller;

import com.zhimou.zhimou_service.myUtil.Result;
import com.zhimou.zhimou_service.pojo.help_seek.LatLon;
import com.zhimou.zhimou_service.pojo.help_seek.HelpSeek;
import com.zhimou.zhimou_service.service.VolunteerPlatformImpl.VolunteerPlatformService1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class VolunteerPlatformController {
    @Autowired
    private VolunteerPlatformService1 service;
    @GetMapping("/get_help_infor")
    public Result getHelpInfor(){
        if(HelpSeek.getLocator()==null){
            return Result.success(new LatLon(null,null,false));
        }else {
            return Result.success(new LatLon(HelpSeek.getLatitude(), HelpSeek.getLongitude(),true));
        }
    }
    @GetMapping("/navi_finished")
    public Result naviFinished(){
        HelpSeek.arrive();
        log.info("navifinished");
        return Result.success(null);
    }
    @GetMapping("/point")
    public Result point(){
        return Result.success(service.getPoint());
    }
    @GetMapping("/is_accept")
    public Result isAccept(boolean isAccept){
        if(!isAccept){
            HelpSeek.clearLocator();
            log.info("Locator cleared");
        }
        return Result.success(null);
    }
    @GetMapping("/clear")
    public Result clear(){
        HelpSeek.clearLocator();
        log.info("Locator cleared");
        return Result.success(null);
    }
    @GetMapping("/help_finished")
    public Result helpFinished(boolean finished){
        if(finished){
            if(service.getPoint()==null){
                service.insertPoint();
            }
            service.addPoint(1+service.getPoint());
            HelpSeek.clearLocator();
            log.info("help finished");
            log.info("Locator cleared");
        }else{
            HelpSeek.clearLocator();
            log.info("help not finish");
            log.info("Locator cleared");
        }
        return Result.success(null);
    }
}
