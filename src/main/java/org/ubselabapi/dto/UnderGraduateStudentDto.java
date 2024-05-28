package org.ubselabapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class UnderGraduateStudentDto {

    private Long id;

    private MultipartFile profile;

    private String name;

    private String email;

    private ArrayList<String> field;


    @Data
    @AllArgsConstructor
    @Builder
    public static class UnderGraduateStudentCreateRequest{

        private List<MultipartFile> profile;

        private String name;

        private String email;

        private ArrayList<String> field;

    }

    @Data
    @AllArgsConstructor
    @Builder
    public static class UnderGraduateStudentUpdateRequest{
        private Long id;
        private String name;
        private String email;
        private ArrayList<String> field;
    }



    @Data
    @AllArgsConstructor
    @Builder
    public static class UnderGraduateStudentResponse{

        private Long id;

        private String profile;

        private String name;

        private String email;

        private ArrayList<String> field;

    }





}
