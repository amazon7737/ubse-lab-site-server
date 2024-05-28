package org.ubselabapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Null;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.ubselabapi.domain.Graduate;
import org.ubselabapi.domain.UnderGraduate;
import org.ubselabapi.dto.GraduateDto;
import org.ubselabapi.dto.ResponseDto;
import org.ubselabapi.dto.UnderGraduateStudentDto;
import org.ubselabapi.service.StudentService;

import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "Student API" , description = "학부생, 졸업생 API 입니다.")
public class StudentController {


    private final StudentService studentService;

    @GetMapping("/undergraduate")
    @Operation(summary = "학부생 전체 조회", description = "학부생 목록 전체 조회입니다.")
    public Result SearchAllUnderGradeuate(){

        ArrayList<Object> students = studentService.findUndergraudates();

        ResponseDto.Response response = new ResponseDto.Response(200, students, "학부생 조회입니다.");

        return new Result<>(response);
    }

    // 이상하게 파일 어로드 하니까 IOException 강요한다. 왜일까?
    @PostMapping("/undergraduate")
    @Operation(summary = "학부생 등록" , description = "학부생 등록 요청입니다. body로 던지시면되고 file은 통째로 주셔야됩니다.")
    public Result addStudent(@ModelAttribute UnderGraduateStudentDto.UnderGraduateStudentCreateRequest dto) throws IOException {


        studentService.saveUndergraduate(dto);

        ResponseDto.Response response = new ResponseDto.Response(200, null, "학생 추가가 완료되었습니다.");


        return new Result<>(response);


    }

    @PatchMapping("/undergraduate")
    @Operation(summary = "학부생 수정" , description = "학부생 수정 요청입니다. body로 던지시면되고 조회했던 데이터에 수정된 데이터 포함시켜서 주시면 됩니다.")
    public Result updateStudent(@RequestBody UnderGraduateStudentDto.UnderGraduateStudentUpdateRequest dto) throws IOException{

        studentService.updateUndergraduate(dto);

        ResponseDto.Response response = new ResponseDto.Response(200, null, "학생 수정이 완료되었습니다.");

        return new Result<>(response);
    }

    @DeleteMapping("/undergraduate")
    @Operation(summary = "학부생 삭제" , description = "학부생 삭제 요청입니다. param로 던지시면되고 String email을 주셔야됩니다.")
    public Result deleteStudent(@RequestParam Long id){
        try{
            studentService.deleteUndergraduate(id);
            ResponseDto.Response response = new ResponseDto.Response(200, null, "학생 삭제가 완료되었습니다.");
            return new Result<>(response);
        }catch (NullPointerException e){
            ResponseDto.Response response = new ResponseDto.Response(401, null, e.getMessage());
            return new Result<>(response);
        }


    }

    @GetMapping("/graduate")
    @Operation(summary = "졸업생 전체 조회", description = "졸업생 전체 조회입니다.")
    public Result SearchAllGraduate(){
        List<Graduate> list = studentService.findGraduates();
         ResponseDto.Response response = new ResponseDto.Response(200, list, "졸업생 조회입니다.");
         return new Result<>(response);
    }

    @PostMapping("/graduate")
    @Operation(summary = "졸업생 등록", description = "졸업생 등록 요청입니다. body로 던지시면됩니다.")
    public Result addGraduate(@RequestBody GraduateDto dto){

        studentService.saveGraduate(dto);
        ResponseDto.Response response = new ResponseDto.Response(200, null, "졸업생 추가가 완료되었습니다.");
        return new Result<>(response);
    }

    @PatchMapping("/graduate")
    @Operation(summary = "졸업생 수정", description = "졸업생 수정 요청입니다. body로 던지시면되고 조회했던 데이터에 수정된 데이터 포함시켜서 주시면 됩니다.")
    public Result updateGraudate(@RequestBody GraduateDto dto){

        studentService.updateGraduate(dto);
        ResponseDto.Response response = new ResponseDto.Response(200, null, "졸업생 수정이 완료되었습니다.");

        return new Result<>(response);
    }

    @DeleteMapping("/graduate")
    @Operation(summary = "졸업생 삭제" , description = "졸업생 삭제 요청입니다. param로 던지시면되고 String email을 주셔야됩니다.")
    public Result deleteGraduate(@RequestParam Long id){

        studentService.deleteGraduate(id);

        ResponseDto.Response response = new ResponseDto.Response(200, null, "졸업생 삭제가 완료되었습니다.");

        return new Result<>(response);
    }

    @Data
    @AllArgsConstructor
    class Result<T>{
        private T data;
    }

 }




