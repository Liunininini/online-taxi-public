package com.mashibing.serviceMap.controller;

import com.example.internalcommon.dto.ResponseResult;
import com.example.internalcommon.request.ForecastPriceDTO;
import com.mashibing.serviceMap.service.DirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/direction")
public class DirectionController {
    @Autowired
    private DirectionService directionService;

    /**
     * 根据起点和终点的经纬度获取距离和时长
     * @param forecastPriceDTO
     * @return
     */
    @GetMapping("/driving")
    public ResponseResult driving(@RequestBody ForecastPriceDTO forecastPriceDTO){

        return directionService.driver(forecastPriceDTO);
    }
}
