package org.ubselabapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;
import org.ubselabapi.domain.ProfessorFiled;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class ProfessorProfileDto {

    private Long profile;

    private String name;

    private String email;

    private List<String> filedList;


    @Data
    @AllArgsConstructor
    @Builder
    public static class createProfileRequest{

        private List<MultipartFile> profile;

        private String name;

        private String email;

        private List<String> field;

    }

    @Data
    @AllArgsConstructor
    @Builder
    public static class updateProfileRequest{

        private String name;

        private String email;

        private ArrayList<String> field;
    }

    @Data
    @AllArgsConstructor
    @Builder
    public static class selectProfileResponse {
        private String profile;

        private String name;

        private String email;

        private List<String> field;
    }



}
