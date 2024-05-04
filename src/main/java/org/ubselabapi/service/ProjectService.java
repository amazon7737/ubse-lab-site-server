package org.ubselabapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.ubselabapi.domain.Project;
import org.ubselabapi.domain.ProjectLink;
import org.ubselabapi.domain.ProjectMember;
import org.ubselabapi.domain.project_type;
import org.ubselabapi.dto.ProjectDto;
import org.ubselabapi.repository.ProjectLinkRepository;
import org.ubselabapi.repository.ProjectMemberRepository;
import org.ubselabapi.repository.ProjectRepository;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    private final ProjectMemberRepository projectMemberRepository;

    private final ProjectLinkRepository projectLinkRepository;

    private final FileService fileService;

    public List<Object> findByProjectTypeProgress(){
       List<Project> projects =  projectRepository.findByProjectType(project_type.진행중);
       ArrayList<Object> memberList = new ArrayList();
       ArrayList<Object> linkList = new ArrayList<>();
       List<Object> resultList = new ArrayList<>();

        // projectMember 찾기
       for(int i=0; i<projects.size();i++) {

           List<ProjectMember> projectMembers = projectMemberRepository.findByProjectId(projects.get(i).getProject_id());
           // 리스트에 객체 넣기
           memberList.add(projectMembers);

       }


       // projectLink 찾기
        for(int i=0; i<projects.size();i ++){
            List<ProjectLink> projectLinks = projectLinkRepository.findByProjectId(projects.get(i).getProject_id());
            linkList.add(projectLinks);
        }

        // 반환할 응답 객체 새로 정렬
       for(int i=0; i<projects.size(); i++){

       // thumb 탐색
       String thumb = fileService.findById(projects.get(i).getThumb()).getUrl();

       // dto
        ProjectDto.selectProjectResponse response = ProjectDto.selectProjectResponse.builder()
                .projectType(projects.get(i).getProject_type())
                .name(projects.get(i).getName())
                .start(projects.get(i).getStart())
                .end(projects.get(i).getEnd())
                .leader(projects.get(i).getLeader())
                .description(projects.get(i).getDescription())
                .thumb(thumb)
                .member(memberList.get(i))
                .link(linkList.get(i))
                .build();

        // 반환할 리스트에 객체 추가
       resultList.add(response);
       }
       return resultList;
    }

    public List<Object> findByProjectTypeComplete(){

        List<Project> projects =  projectRepository.findByProjectType(project_type.완료);
        ArrayList<List<ProjectMember>> memberList = new ArrayList<>();
        ArrayList<List<ProjectLink>> linkList = new ArrayList<>();
        List<Object> resultList = new ArrayList<>();

        // projectMember 찾기
        for(int i=0; i<projects.size();i++) {

            List<ProjectMember> projectMembers = projectMemberRepository.findByProjectId(projects.get(i).getProject_id());
            // 리스트에 객체 넣기
            memberList.add(projectMembers);

        }

        // projectLink 찾기
        for(int i=0; i<projects.size();i ++){
            List<ProjectLink> projectLinks = projectLinkRepository.findByProjectId(projects.get(i).getProject_id());
            linkList.add(projectLinks);
        }

        // 반환할 응답 객체 새로 정렬
        for(int i=0; i<projects.size(); i++){

            // thumb 탐색
            String thumb = fileService.findById(projects.get(i).getThumb()).getUrl();

            ProjectDto.selectProjectResponse response = ProjectDto.selectProjectResponse.builder()
                    .projectType(projects.get(i).getProject_type())
                    .name(projects.get(i).getName())
                    .start(projects.get(i).getStart())
                    .end(projects.get(i).getEnd())
                    .leader(projects.get(i).getLeader())
                    .description(projects.get(i).getDescription())
                    .thumb(thumb)
                    .member(memberList.get(i))
                    .link(linkList.get(i))
                    .build();

            // 반환할 리스트에 객체 추가
            resultList.add(response);
        }
        return resultList;
    }

    public void saveProject(ProjectDto.createProjectRequest dto) throws IOException {

        /**
         * 프로젝트에 넣을 builder
         */
        Long fileId = fileService.uploadFile(dto.getThumb());

        Project project = Project.builder()
                .name(dto.getName())
                .thumb(fileId)
                .start(dto.getStart())
                .end(dto.getEnd())
                .description(dto.getDescription())
                .leader(dto.getLeader())
                .project_type(dto.getProjectType())
                .build();


        projectRepository.save(project);

        /**
         *
         */
        ArrayList<ProjectMember> list = dto.getMembers();

        for(int i =0; i<list.size();i++){
            ProjectMember projectMember = ProjectMember.builder()
                    .project_id(list.get(i).getProject_id())
                    .name(list.get(i).getName())
                    .build();

            projectMemberRepository.save(projectMember);
        }

        /**
         *
         */
        ArrayList<ProjectLink> linkList = dto.getLinks();



        for(int i=0; i<linkList.size();i++){
            ProjectLink link = ProjectLink.builder()
                        .project_id(linkList.get(i).getProject_id())
                        .link(linkList.get(i).getLink())
                        .build();

            projectLinkRepository.save(link);

        }



    }


    public void deleteProject(Long projectId){

        Project project = projectRepository.findById(projectId).get();
        projectRepository.deleteById(projectId);
        projectMemberRepository.deleteById(projectId);
        projectLinkRepository.deleteById(projectId);
        fileService.deleteImage(project.getThumb());

    }


}
