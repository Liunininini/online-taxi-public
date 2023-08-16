package com.mashibing.serviceMap.service;

import com.example.internalcommon.dto.ResponseResult;
import com.example.internalcommon.request.ForecastPriceDTO;
import com.example.internalcommon.responese.DirectionResponse;
import com.mashibing.serviceMap.remote.MapDirectionClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DirectionService {
    @Autowired
    private MapDirectionClient mapDirectionClient;

    public ResponseResult driver(ForecastPriceDTO forecastPriceDTO) {
        DirectionResponse directionResponse = mapDirectionClient.direction(forecastPriceDTO);
        return ResponseResult.success(directionResponse);
    }
}
