package com.mashibing.apipassenger.controller;

import com.example.internalcommon.dto.ResponseResult;
import com.example.internalcommon.request.ForecastPriceDTO;
import com.mashibing.apipassenger.service.ForecastPriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ForecastPriceController {


    @Autowired
    private ForecastPriceService forecastPriceService;

    /**
     * 预估价格
     * @param forecastPriceDTO
     * @return
     */
    @PostMapping("/forecast-price")
    public ResponseResult forecastPrice(@RequestBody ForecastPriceDTO forecastPriceDTO){

        log.info("出发地经度:{}，出发地维度:{}，目的地经度：{}，目的地维度：{}。", forecastPriceDTO.getDepLongitude(), forecastPriceDTO.getDepLatitude(), forecastPriceDTO.getDestLongitude(), forecastPriceDTO.getDestLatitude());
        return forecastPriceService.forecastPrice(forecastPriceDTO);
    }
}
