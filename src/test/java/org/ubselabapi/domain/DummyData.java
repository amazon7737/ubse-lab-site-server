package org.ubselabapi.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.ubselabapi.repository.*;
import org.ubselabapi.service.FileService;

@SpringBootTest
//@Slf4j
public class DummyData {

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    ProfessorFiledRepository professorFiledRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    ProjectLinkRepository projectLinkRepository;

    @Autowired
    ProjectMemberRepository projectMemberRepository;

    @Autowired
    UnderGraduateRepository underGraduateRepository;

    @Autowired
    GraduateRepository graduateRepository;

    @Autowired
    UndergraduateFiledRepository undergraduateFiledRepository;

    @Autowired
    ImageRepository imageRepository;

    @Test
    void imageInputData(){

        Image image = Image.builder()
                .url("https://raw.githubusercontent.com/qqaazz0222/Lab-UbSE/main/src/pages/manageMember/profiles/no-profile.jpg")
                .build();

        imageRepository.save(image);



    }

    @Test
    void professorDataInput(){

        Professor professor = Professor.builder()
                .email("mkmoon@dongseo.ac.kr")
                .name("문미경")
                .profile(1L)
                .build();

        professorRepository.save(professor);

    }

    @Test
    void professorFiledDataInput(){
        ProfessorFiled professorFiled = ProfessorFiled.builder()
                .professor_id(1L)
                .data("소프트웨어 프로덕트라인")
                .build();

        ProfessorFiled professorFiled2 = ProfessorFiled.builder()
                .professor_id(1L)
                .data("IEEE Transactions on Software Engineering")
                .build();

        ProfessorFiled professorFiled3 = ProfessorFiled.builder()
                .professor_id(1L)
                .data("RFID 미들웨어")
                .build();


        professorFiledRepository.save(professorFiled);
        professorFiledRepository.save(professorFiled2);
        professorFiledRepository.save(professorFiled3);

    }

//    @Test
//    void professorDataSearch(){

//        Professor professor = professorRepository.findById(1L).get();
//        List<ProfessorFiled> professorFiled = professorFiledRepository.findByProfessorId(1L);
//
//        ArrayList list = new ArrayList();
//        list.add(professor);
//        list.add(professorFiled);


//        for(int i =0;i<list.size();i++){
//            log.info("list: {}", list);
//        }

//    }

    @Test
    void undergraduateData(){

        UnderGraduate underGraduate = UnderGraduate.builder()
                .email("honggildong@dongseo.ac.kr")
                .name("홍길동")
                .profile(1L)
                .build();

        UnderGraduate underGraduate2 = UnderGraduate.builder()
                .email("honggildong@dongseo.ac.kr")
                .name("홍길동")
                .profile(1L)
                .build();

        UnderGraduate underGraduate3 = UnderGraduate.builder()
                .email("honggildong@dongseo.ac.kr")
                .name("홍길동")
                .profile(1L)
                .build();

        UnderGraduate underGraduate4 = UnderGraduate.builder()
                .email("honggildong@dongseo.ac.kr")
                .name("홍길동")
                .profile(1L)
                .build();


        Long id = underGraduateRepository.save(underGraduate).getId();
        Long id2 = underGraduateRepository.save(underGraduate2).getId();
        Long id3 = underGraduateRepository.save(underGraduate3).getId();
        Long id4 = underGraduateRepository.save(underGraduate4).getId();

        UndergraduateFiled undergraduateFiled = UndergraduateFiled.builder()
                .undergraduateId(id)
                .filed("인공지능")
                .build();

        UndergraduateFiled undergraduateFiled2 = UndergraduateFiled.builder()
                .undergraduateId(id2)
                .filed("머신러닝")
                .build();

        UndergraduateFiled undergraduateFiled3 = UndergraduateFiled.builder()
                .undergraduateId(id3)
                .filed("데이터베이스")
                .build();

        UndergraduateFiled undergraduateFiled4 = UndergraduateFiled.builder()
                .undergraduateId(id4)
                .filed("머신러닝")
                .build();



        undergraduateFiledRepository.save(undergraduateFiled);
        undergraduateFiledRepository.save(undergraduateFiled2);
        undergraduateFiledRepository.save(undergraduateFiled3);
        undergraduateFiledRepository.save(undergraduateFiled4);



    }



    @Test
    void graduateData(){

        Graduate graduate = Graduate.builder()
                .email("honggildong@dongseo.ac.kr")
                .name("홍길동")
//                .profile(null)
                .graduated_date(2024L)
                .company("(주)유비에스이")
                .build();

        Graduate graduate2 = Graduate.builder()
                .email("honggildong@dongseo.ac.kr")
                .name("홍길동")
//                .profile(null)
                .graduated_date(2024L)
                .company("(주)유비에스이")
                .build();
        Graduate graduate3 = Graduate.builder()
                .email("honggildong@dongseo.ac.kr")
                .name("홍길동")
//                .profile(null)
                .graduated_date(2024L)
                .company("(주)유비에스이")
                .build();

        Graduate graduate4 = Graduate.builder()
                .email("honggildong@dongseo.ac.kr")
                .name("홍길동")
//                .profile(null)
                .graduated_date(2024L)
                .company("(주)유비에스이")
                .build();

        graduateRepository.save(graduate);
        graduateRepository.save(graduate2);
        graduateRepository.save(graduate3);
        graduateRepository.save(graduate4);

    }


    @Test
    void progressData(){

        Project project = Project.builder()
                .project_type(project_type.진행중)
                .name("프로젝트명")
                .start("2024-01-01")
                .end("현재")
                .description("프로젝트에 대한 설명입니다.")
                .leader("홍길동")
                .thumb(1L)
                .build();

        Project project2 = Project.builder()
                .project_type(project_type.진행중)
                .name("프로젝트명")
                .start("2024-01-01")
                .end("현재")
                .description("프로젝트에 대한 설명입니다.")
                .leader("홍길동")
                .thumb(1L)
                .build();


        Project project3 = Project.builder()
                .project_type(project_type.진행중)
                .name("프로젝트명")
                .start("2024-01-01")
                .end("현재")
                .description("프로젝트에 대한 설명입니다.")
                .leader("홍길동")
                .thumb(1L)
                .build();

        Project project4 = Project.builder()
                .project_type(project_type.진행중)
                .name("프로젝트명")
                .start("2024-01-01")
                .end("현재")
                .description("프로젝트에 대한 설명입니다.")
                .leader("홍길동")
                .thumb(1L)
                .build();

        projectRepository.save(project);
        projectRepository.save(project2);
        projectRepository.save(project3);
        projectRepository.save(project4);

    }

    @Test
    void completeData(){

        Project project = Project.builder()
                .project_type(project_type.완료)
                .name("프로젝트명")
                .start("2024-01-01")
                .end("2024-12-31")
                .description("프로젝트에 대한 설명입니다.")
                .leader("홍길동")
                .thumb(1L)
                .build();

        Project project2 = Project.builder()
                .project_type(project_type.완료)
                .name("프로젝트명")
                .start("2024-01-01")
                .end("2024-12-31")
                .description("프로젝트에 대한 설명입니다.")
                .leader("홍길동")
                .thumb(1L)
                .build();

        Project project3 = Project.builder()
                .project_type(project_type.완료)
                .name("프로젝트명")
                .start("2024-01-01")
                .end("2024-12-31")
                .description("프로젝트에 대한 설명입니다.")
                .leader("홍길동")
                .thumb(1L)
                .build();

        Project project4 = Project.builder()
                .project_type(project_type.완료)
                .name("프로젝트명")
                .start("2024-01-01")
                .end("2024-12-31")
                .description("프로젝트에 대한 설명입니다.")
                .leader("홍길동")
                .thumb(1L)
                .build();

        projectRepository.save(project);
        projectRepository.save(project2);
        projectRepository.save(project3);
        projectRepository.save(project4);


    }

    @Test
    void projectMemberData(){

        ProjectMember projectMember = ProjectMember.builder()
                .name("홍길동")
                .role(Role.백엔드)
                .project_id(1L)
                .build();

        ProjectMember projectMember2 = ProjectMember.builder()
                .name("홍길동")
                .role(Role.프론트엔드)
                .project_id(1L)
                .build();

        ProjectMember projectMember3 = ProjectMember.builder()
                .name("홍길동")
                .role(Role.백엔드)
                .project_id(2L)
                .build();

        ProjectMember projectMember4 = ProjectMember.builder()
                .name("홍길동")
                .role(Role.프론트엔드)
                .project_id(2L)
                .build();
        ProjectMember projectMember5 = ProjectMember.builder()
                .name("홍길동")
                .role(Role.백엔드)
                .project_id(3L)
                .build();
        ProjectMember projectMember6 = ProjectMember.builder()
                .name("홍길동")
                .role(Role.프론트엔드)
                .project_id(3L)
                .build();
        ProjectMember projectMember7 = ProjectMember.builder()
                .name("홍길동")
                .role(Role.백엔드)
                .project_id(4L)
                .build();

        ProjectMember projectMember8 = ProjectMember.builder()
                .name("홍길동")
                .role(Role.프론트엔드)
                .project_id(4L)
                .build();

        projectMemberRepository.save(projectMember);
        projectMemberRepository.save(projectMember2);
        projectMemberRepository.save(projectMember3);
        projectMemberRepository.save(projectMember4);
        projectMemberRepository.save(projectMember5);
        projectMemberRepository.save(projectMember6);
        projectMemberRepository.save(projectMember7);
        projectMemberRepository.save(projectMember8);

    }


    @Test
    void projectLinkData(){

        ProjectLink projectLink = ProjectLink.builder()
                .project_id(1L)
                .link("https://github.com/qqaazz0222")
                .type("git")
                .build();

        ProjectLink projectLink2 = ProjectLink.builder()
                .project_id(2L)
                .link("https://github.com/qqaazz0222")
                .type("git")
                .build();

        ProjectLink projectLink3 = ProjectLink.builder()
                .project_id(3L)
                .link("https://github.com/qqaazz0222")
                .type("git")
                .build();

        ProjectLink projectLink4 = ProjectLink.builder()
                .project_id(4L)
                .link("https://github.com/qqaazz0222")
                .type("git")
                .build();

        ProjectLink projectLink5 = ProjectLink.builder()
                .project_id(5L)
                .link("https://github.com/qqaazz0222")
                .type("git")
                .build();

        ProjectLink projectLink6 = ProjectLink.builder()
                .project_id(6L)
                .link("https://github.com/qqaazz0222")
                .type("git")
                .build();
        ProjectLink projectLink7 = ProjectLink.builder()
                .project_id(7L)
                .link("https://github.com/qqaazz0222")
                .type("git")
                .build();
        ProjectLink projectLink8 = ProjectLink.builder()
                .project_id(8L)
                .link("https://github.com/qqaazz0222")
                .type("git")
                .build();

        projectLinkRepository.save(projectLink);
        projectLinkRepository.save(projectLink2);
        projectLinkRepository.save(projectLink3);
        projectLinkRepository.save(projectLink4);
        projectLinkRepository.save(projectLink5);
        projectLinkRepository.save(projectLink6);
        projectLinkRepository.save(projectLink7);
        projectLinkRepository.save(projectLink8);

    }




}
