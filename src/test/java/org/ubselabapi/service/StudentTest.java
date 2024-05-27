//package org.ubselabapi.service;
//
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.multipart.MultipartFile;
//import org.ubselabapi.domain.UnderGraduate;
//import org.ubselabapi.domain.UndergraduateFiled;
//import org.ubselabapi.dto.UnderGraduateStudentDto;
//import org.ubselabapi.repository.UnderGraduateRepository;
//import org.ubselabapi.repository.UndergraduateFiledRepository;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//@SpringBootTest
//@Slf4j
//public class StudentTest {
//
//    @Autowired
//    StudentService studentService;
//
//    @Autowired
//    UndergraduateFiledRepository undergraduateFiledRepository;
//
//
//    @Test
//    @Transactional
//    void UndergraduateFiledSaveTest() throws IOException{
//
//
//    }
//
//    @Test
//    @Transactional
//    void UndergraduateFiledUpdateTest() throws IOException {
//
//        Long id = 1L;
//
//        ArrayList<String> filedList = new ArrayList<String>();
//
//        MultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "Spring !!!!".getBytes());
//
//        filedList.add("머신러닝");
//        filedList.add("백엔드");
//
//        UnderGraduateStudentDto dto = UnderGraduateStudentDto.builder()
//                        .filed(filedList)
//                        .id(1L)
//                        .name("김강민")
//                        .profile(file)
//                        .email("amazon7737@gmail.com")
//                        .build();
//
//        studentService.updateUndergraduate(dto);
//
//
//
//
//        List<UndergraduateFiled> undergraduateFiled = undergraduateFiledRepository.findByUndergraduateId(1L);
//
//        Assertions.assertEquals(filedList.get(0), undergraduateFiled.get(1).getFiled());
//        Assertions.assertEquals(filedList.get(1), undergraduateFiled.get(2).getFiled());
//
//    }
//}
