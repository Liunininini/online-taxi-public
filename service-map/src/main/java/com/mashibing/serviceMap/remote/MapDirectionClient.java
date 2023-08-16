package com.mashibing.serviceMap.remote;

import com.example.internalcommon.constant.AmapConfigConstants;
import com.example.internalcommon.request.ForecastPriceDTO;
import com.example.internalcommon.responese.DirectionResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.OptionalInt;

@Service
@Slf4j
public class MapDirectionClient {

    @Value("${amap.key}")
    private String amapKey;

    @Autowired
    private RestTemplate restTemplate;

    public DirectionResponse direction(ForecastPriceDTO forecastPriceDTO){
        //组装接口url
        StringBuilder sb = new StringBuilder();
        sb.append(AmapConfigConstants.DIRECTION_URL)
                .append("?")
                .append("origin=").append(forecastPriceDTO.getDepLongitude()).append(",").append(forecastPriceDTO.getDepLatitude())
                .append("&")
                .append("destination=").append(forecastPriceDTO.getDestLongitude()).append(",").append(forecastPriceDTO.getDestLatitude())
                .append("&")
                .append("key=").append(amapKey)
                .append("&").append("output=JSON")
                .append("&")
                .append("show_fields=").append("cost");
        log.info(sb.toString());
        //调用高德Api
        ResponseEntity<String> directEntity = restTemplate.getForEntity(sb.toString(), String.class);

        String directionString = directEntity.getBody();
        log.info("高德地图:路径规划返回信息:"+directionString);
        //解析接口
       return parseDirectionEntity(directionString);
    }

    private DirectionResponse parseDirectionEntity(String directionString){
        DirectionResponse directionResponse = null;
        try {
            directionResponse= new DirectionResponse();
            JSONObject result = JSONObject.fromObject(directionString);
           if (result.has(AmapConfigConstants.STATUS)){
                int status = result.getInt(AmapConfigConstants.STATUS);
                if (status == 1){
                    if (result.has(AmapConfigConstants.ROUTE)){
                        JSONObject routeObject = result.getJSONObject(AmapConfigConstants.ROUTE);
                        JSONArray pathsArray = routeObject.getJSONArray(AmapConfigConstants.PATHS);
                        JSONObject pathsObject = pathsArray.getJSONObject(0);
                        int distance = OptionalInt.of(pathsObject.getInt(AmapConfigConstants.DISTANCE)).orElse(0);
                        directionResponse.setDistance(distance);
                        JSONObject costObject = pathsObject.getJSONObject(AmapConfigConstants.COST);
                        int duration = OptionalInt.of(costObject.getInt(AmapConfigConstants.DURATION)).orElse(0);
                        directionResponse.setDuration(duration);

                    }
                }
            }

        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return directionResponse;
    }
}
