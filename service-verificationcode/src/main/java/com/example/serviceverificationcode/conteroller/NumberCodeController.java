package com.example.serviceverificationcode.conteroller;

import com.example.internalcommon.dto.ResponseResult;
import com.example.internalcommon.responese.NumberCodeResponse;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NumberCodeController {


    @GetMapping("/numberCode")
    public ResponseResult numberCode(@RequestParam("size") int size) {
        //生成验证码

        System.out.println("size" + size);

        //获取随机数
        double mathRandom = (Math.random() * 9 + 1) * Math.pow(10, size - 1);
        int resultInt = (int) mathRandom;

//        JSONObject result = new JSONObject();
//        result.put("code", 1);
//        result.put("success", "success");
//        JSONObject data = new JSONObject();
//        data.put("numberCode", resultInt);
//        result.put("data", data);
        NumberCodeResponse data = new NumberCodeResponse();
        data.setNumberCode(resultInt);
        return ResponseResult.success(data);
    }


}
