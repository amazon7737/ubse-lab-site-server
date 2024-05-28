package org.ubselabapi.dto;

import io.swagger.v3.oas.models.links.Link;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import org.ubselabapi.domain.ProjectLink;
import org.ubselabapi.domain.ProjectMember;
import org.ubselabapi.domain.UnderGraduate;
import org.ubselabapi.domain.project_type;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class ProjectDto {


    private Long thumb;

    private String name;

    private String start;

    private String end;

    private String description;

    private String leader;

    private project_type projectType;


    @Data
    @AllArgsConstructor
    public static class createProjectRequest{
        private MultipartFile thumb;

        private String name;

        private String start;

        private String end;

        private String description;

        private String leader;

        private project_type projectType;

        private ArrayList<ProjectMember> members;

        private ArrayList<ProjectLink> links;

    }

    @Data
    @AllArgsConstructor
    @Builder
    public static class selectProjectResponse{

        private Long id;

        private String thumb;

        private String name;

        private String start;

        private String end;

        private String description;

        private String leader;

        private project_type projectType;

        private Object member;

        private Object link;
    }




}
