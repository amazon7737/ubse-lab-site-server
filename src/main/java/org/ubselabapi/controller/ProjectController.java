package org.ubselabapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.ubselabapi.domain.Project;
import org.ubselabapi.dto.LinkDto;
import org.ubselabapi.dto.ProjectDto;
import org.ubselabapi.dto.ResponseDto;
import org.ubselabapi.service.ProjectService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "프로젝트 API", description = "진행중인 , 완료된 프로젝트 API 입니다.")
public class ProjectController {

    private final ProjectService projectService;


    @GetMapping("/progress")
    @Operation(summary = "진행중인 프로젝트 전체 조회", description = "진행중인 프로젝트 전체 조회입니다.")
    public Result SearchProgress(){

        List<Object> list = projectService.findByProjectTypeProgress();


        ResponseDto.Response response = new ResponseDto.Response(200, list, "진행중인 프로젝트 조회입니다.");

        return new Result<>(response);

    }

    @PostMapping("/progress/add")
    @Operation(summary = "진행중인 프로젝트 등록", description = "진행중인 프로젝트 등록 요청입니다.")
    public Result addProgress(@RequestBody ProjectDto.createProjectRequest dto) throws IOException {

        projectService.saveProject(dto);

        ResponseDto.Response response = new ResponseDto.Response(200, null, "프로젝트 추가가 완료되었습니다.");

        return new Result<>(response);

    }

    @PostMapping("/progress/update")
    @Operation(summary = "진행중인 프로젝트 수정", description = "진행중인 프로젝트 수정 요청입니다.")
    public Result updateProgress(@RequestBody ProjectDto.createProjectRequest dto) throws IOException{


        projectService.saveProject(dto);

        ResponseDto.Response response = new ResponseDto.Response(200, null, "프로젝트 수정이 완료되었습니다.");

        return new Result<>(response);

    }

    @PostMapping("/progress/delete")
    @Operation(summary = "진행중인 프로젝트 삭제", description = "진행중인 프로젝트 삭제 요청입니다.")
    public Result deleteProgress(@RequestBody Long projectId){

        projectService.deleteProject(projectId);


        ResponseDto.Response response = new ResponseDto.Response(200, null, "프로젝트 삭제가 완료되었습니다.");

        return new Result<>(response);
    }

    @GetMapping("/complete")
    @Operation(summary = "완료된 프로젝트 전체 조회", description = "완료된 프로젝트 전체 조회 요청입니다.")
    public Result SearchComplete(){

        List<Object> list=  projectService.findByProjectTypeComplete();


        ResponseDto.Response response = new ResponseDto.Response(200, list, "완료된 프로젝트 조회입니다.");

        return new Result<>(response);
    }


    @PostMapping("/complete/add")
    @Operation(summary = "완료된 프로젝트 등록", description = "완료된 프로젝트 등록 요청입니다.")
    public Result addComplete(@RequestBody ProjectDto.createProjectRequest dto) throws IOException{

        projectService.saveProject(dto);

        ResponseDto.Response response = new ResponseDto.Response(200, null, "프로젝트 등록이 완료되었습니다.");

        return new Result<>(response);
    }

    @PostMapping("/complete/update")
    @Operation(summary = "완료된 프로젝트 수정", description = "완료된 프로젝트 수정 요청입니다.")
    public Result updateComplete(@RequestBody ProjectDto.createProjectRequest dto) throws IOException{


        projectService.saveProject(dto);

        ResponseDto.Response response = new ResponseDto.Response(200, null, "프로젝트 수정이 완료되었습니다.");
        return new Result<>(response);
    }

    @PostMapping("/complete/delete")
    @Operation(summary = "완료된 프로젝트 삭제" , description = "완료된 프로젝트 삭제 요청입니다.")
    public Result deleteComplete(@RequestBody Long projectId){

        projectService.deleteProject(projectId);

        ResponseDto.Response response = new ResponseDto.Response(200, null, "프로젝트 삭제가 완료되었습니다.");

        return new Result<>(response);
    }





    @Data
    @AllArgsConstructor
    class Result<T>{
        private T data;
    }




}
