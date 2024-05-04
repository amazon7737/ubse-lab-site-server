package org.ubselabapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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
    @PostMapping("/undergraduate/add")
    @Operation(summary = "학부생 등록" , description = "학부생 등록 요청입니다.")
    public Result addStudent(@RequestBody UnderGraduateStudentDto dto) throws IOException {


        studentService.saveUndergraduate(dto);

        ResponseDto.Response response = new ResponseDto.Response(200, null, "학생 추가가 완료되었습니다.");


        return new Result<>(response);


    }

    @PostMapping("/undergraduate/update")
    @Operation(summary = "학부생 수정" , description = "학부생 수정 요청입니다.")
    public Result updateStudent(@RequestBody UnderGraduateStudentDto dto) throws IOException{

        studentService.updateUndergraduate(dto);

        ResponseDto.Response response = new ResponseDto.Response(200, null, "학생 수정이 완료되었습니다.");


        return new Result<>(response);
    }

    @PostMapping("/undergraduate/delete")
    @Operation(summary = "학부생 삭제" , description = "학부생 삭제 요청입니다.")
    public Result deleteStudent(@RequestBody Long StudentId){
        studentService.deleteUndergraduate(StudentId);

        ResponseDto.Response response = new ResponseDto.Response(200, null, "학생 삭제가 완료되었습니다.");

        return new Result<>(response);
    }

    @GetMapping("/graduate")
    @Operation(summary = "졸업생 전체 조회", description = "졸업생 전체 조회입니다.")
    public Result SearchAllGraduate(){


        List<Graduate> list = studentService.findGraduates();
         ResponseDto.Response response = new ResponseDto.Response(200, list, "졸업생 조회입니다.");
         return new Result<>(response);
    }

    @PostMapping("/graduate/add")
    @Operation(summary = "졸업생 등록", description = "졸업생 등록 요청입니다.")
    public Result addGraduate(@RequestBody GraduateDto dto){

        studentService.saveGraduate(dto);
        ResponseDto.Response response = new ResponseDto.Response(200, null, "졸업생 추가가 완료되었습니다.");
        return new Result<>(response);
    }

    @PostMapping("/graduate/update")
    @Operation(summary = "졸업생 수정", description = "졸업생 수정 요청입니다.")
    public Result updateGraudate(@RequestBody GraduateDto dto){

        studentService.updateGraduate(dto);
        ResponseDto.Response response = new ResponseDto.Response(200, null, "졸업생 수정이 완료되었습니다.");

        return new Result<>(response);
    }

    @PostMapping("/graduate/delete")
    @Operation(summary = "졸업생 삭제" , description = "졸업생 삭제 요청입니다.")
    public Result deleteGraduate(@RequestBody Long studentId){

        studentService.deleteGraduate(studentId);

        ResponseDto.Response response = new ResponseDto.Response(200, null, "졸업생 삭제가 완료되었습니다.");

        return new Result<>(response);
    }

    @Data
    @AllArgsConstructor
    class Result<T>{
        private T data;
    }

 }




