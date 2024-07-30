package com.zhimou.zhimou_service.pojo.help_seek;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LatLon {
    private Double latitude;
    private Double longitude;
    private boolean existHelpSeek;
    private Integer helpType; //0: 主动普通求助 1：主动紧急求助 2：被动求助
    private Integer normalType; //0:购物 1：迷路 2：非普通求助
}
