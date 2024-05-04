package org.ubselabapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ubselabapi.dto.ResponseDto;

@RestController
public class HomeController {

    @GetMapping("/")
    @Operation(summary = "홈", description = "홈입니다.")
    public Result Home(){
        String hello = "안녕하세요! UBSE-LAB 사이트 API 서버입니다.";

        ResponseDto.Response response = new ResponseDto.Response(200, null, hello);

        return new Result<>(response);

    }

    @Data
    @AllArgsConstructor
    class Result<T>{
        private T data;
    }
}
