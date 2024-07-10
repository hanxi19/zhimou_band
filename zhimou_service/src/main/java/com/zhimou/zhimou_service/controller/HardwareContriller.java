package com.zhimou.zhimou_service.controller;

import com.zhimou.zhimou_service.myUtil.Result;
import com.zhimou.zhimou_service.pojo.help_seek.HelpSeek;
import com.zhimou.zhimou_service.pojo.help_seek.LatLon;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;
@Slf4j
@RestController
public class HardwareContriller {
    @PostMapping("/post_help_infor")
    public Result postHelpInfor(@RequestBody LatLon latLon){
        if(HelpSeek.getLocator()==null) {
            log.info("locator added");
            HelpSeek.setLoacator(latLon.getLatitude(), latLon.getLongitude());
        }else {
            log.info("locator has existed");
        }
        return Result.success(null);
    }
}
