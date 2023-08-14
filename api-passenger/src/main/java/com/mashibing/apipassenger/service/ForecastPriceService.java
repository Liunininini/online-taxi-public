package com.mashibing.apipassenger.service;

import com.example.internalcommon.dto.ResponseResult;
import com.example.internalcommon.request.ForecastPriceDTO;
import com.example.internalcommon.responese.ForecastPriceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Slf4j
public class ForecastPriceService {

    public ResponseResult forecastPrice(ForecastPriceDTO forecastPriceDTO){
        //出发地经度
        String depLongitude = forecastPriceDTO.getDepLongitude();
        //出发地维度
        String depLatitude = forecastPriceDTO.getDepLatitude();
        //目的地经度
        String destLongitude = forecastPriceDTO.getDestLongitude();
        //目的地维度
        String destLatitude = forecastPriceDTO.getDestLatitude();

        log.info("Forecast price");

        ForecastPriceResponse response = new ForecastPriceResponse();
        response.setPrice("12.34");
        return ResponseResult.success(response);
    }
}
