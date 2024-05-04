package org.ubselabapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.ubselabapi.dto.ProfessorProfileDto;
import org.ubselabapi.dto.ResponseDto;
import org.ubselabapi.service.ProfessorService;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "교수님 프로필 API")
public class ProfessorController {

    private final ProfessorService professorService;

    @GetMapping("/professor")
    @Operation(summary = "교수님 프로필 조회" , description = "교수님 프로필 전체 조회입니다.")
    public Result professorProfile(){

        ProfessorProfileDto.selectProfileRequest response = professorService.findByProfessorInfo();

        return new Result(response);

    }


    @PostMapping("/professor")
    @Operation(summary = "교수님 프로필 수정", description = "교수님 프로필 수정 요청입니다.")
    public Result professorProfileUpdate(@RequestBody ProfessorProfileDto.updateProfileRequest dto) throws IOException {

        professorService.updateProfessorInfo(dto);

        ResponseDto.Response response = new ResponseDto.Response(200, null, "교수님 프로필 수정이 완료되었습니다.");

        return new Result(response);
    }

    @Data
    @AllArgsConstructor
    class Result<T>{
        private T data;
    }



}
