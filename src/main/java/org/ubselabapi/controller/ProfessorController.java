package org.ubselabapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
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

        ProfessorProfileDto.selectProfileResponse response = professorService.findByProfessorInfo();

        return new Result(response);

    }


    @PatchMapping("/professor")
    @Operation(summary = "교수님 프로필 수정", description = "교수님 프로필 수정 요청입니다. body로 던지시면되고 조회했던 데이터에 수정된 데이터 포함시켜서 주시면 됩니다.")
    public Result professorProfileUpdate(@RequestBody @Parameter(name = "thumb" , description = "이미지 파일 번호") ProfessorProfileDto.updateProfileRequest dto) throws IOException {

        professorService.updateProfessorInfo(dto);

        ResponseDto.Response response = new ResponseDto.Response(200, null, "교수님 프로필 수정이 완료되었습니다.");

        return new Result(response);
    }

//    @PostMapping("/professor")
//    @Operation(summary = "교수님 프로필 생성", description = "교수님 프로필 생성 요청입니다. body로 던지시면되고 file로 통째로 주셔야됩니다.")
//    public Result professorProfileCreate(@ModelAttribute ProfessorProfileDto.createProfileRequest dto) throws IOException{
//
//        professorService.createProfessorInfo(dto);
//
//        ResponseDto.Response response = new ResponseDto.Response(200, null, "교수님 프로필 생성이 완료되었습니다.");
//
//        return new Result(response);
//    }


//    @DeleteMapping("/professor")
//    @Operation(summary = "교수님 프로필 삭제", description = "교수님 프로필 삭제 요청입니다. param로 던지시면되고 String professorName으로 주셔야됩니다.")
//    public Result professorProfileDelete(@RequestParam String professorName) throws JSONException {
//
//        professorService.deleteProfessorInfo(professorName);
//
//        ResponseDto.Response response = new ResponseDto.Response(200, null, "교수님 프로필 삭제가 완료되었습니다.");
//
//        return new Result(response);
//    }


    @Data
    @AllArgsConstructor
    class Result<T>{
        private T data;
    }



}
