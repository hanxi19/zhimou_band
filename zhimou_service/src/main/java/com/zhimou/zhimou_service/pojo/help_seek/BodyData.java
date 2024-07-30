package com.zhimou.zhimou_service.pojo.help_seek;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BodyData {
    private List<Integer> bloodOxygen;
    private List<Integer> heartRate;
}
