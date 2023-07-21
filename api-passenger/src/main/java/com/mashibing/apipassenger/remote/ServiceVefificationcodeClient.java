package com.mashibing.apipassenger.remote;

import com.example.internalcommon.dto.ResponseResult;
import com.example.internalcommon.responese.NumberCodeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("service-verificationcode")
public interface ServiceVefificationcodeClient {

    @RequestMapping(method = RequestMethod.GET,value = "/numberCode")
    ResponseResult<NumberCodeResponse> getNumberCode(@RequestParam("size")int size);
}
