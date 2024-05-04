package org.ubselabapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import org.ubselabapi.domain.UnderGraduate;
import org.ubselabapi.domain.filed;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@Builder
public class UnderGraduateStudentDto {

    private MultipartFile profile;

    private String name;

    private String email;

    private filed filed;




    @Data
    @AllArgsConstructor
    @Builder
    public static class UnderGraduateStudentResponse{
        private String profile;

        private String name;

        private String email;

        private filed filed;

    }



}
