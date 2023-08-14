package com.mashibing.servicePrice.service;

import com.example.internalcommon.dto.ResponseResult;
import com.example.internalcommon.request.ForecastPriceDTO;
import com.example.internalcommon.responese.ForecastPriceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

        log.info("调用地图服务,查询距离和时长");

        log.info("读取计价规则");

        log.info("根据距离、时长、计价规则、车型来计算价格");

        ForecastPriceResponse response = new ForecastPriceResponse();
        response.setPrice("12.34");
        return ResponseResult.success(response);
    }
}
