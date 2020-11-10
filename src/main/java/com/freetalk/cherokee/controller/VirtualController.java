package com.freetalk.cherokee.controller;

import com.freetalk.cherokee.dto.ModelInfoDTO;
import com.freetalk.cherokee.dto.Result;
import com.freetalk.cherokee.exception.BaseException;
import com.freetalk.cherokee.exception.SystemErrorType;
import com.freetalk.cherokee.service.VirtualService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

@Slf4j
@RestController
public class VirtualController {


    @Autowired
    private VirtualService virtualService;


    /**
     * @Description: 获取模型信息
     * @author doujl
     * @Date 2020-10-27 17:23:50
     */
    @ResponseBody
    @PostMapping(value = "/virtual/getModelInfo", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result getModelInfo(@RequestParam("name") String name) {
        log.info("getModelInfo| name={}", name);

        if (StringUtils.isEmpty(name)) {
            throw new BaseException(SystemErrorType.ARGUMENT_NOT_VALID);
        }
        Result result;
        try {
            ModelInfoDTO modelInfo = virtualService.getModelInfoById(name);
            result = Result.success(modelInfo);
        } catch (Exception e) {
            result = Result.fail();
            log.error("根据id获取模型id接口异常，异常信息：" + e);
        }
        return result;
    }
}
