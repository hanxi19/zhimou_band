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
}
