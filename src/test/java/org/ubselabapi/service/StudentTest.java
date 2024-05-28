package org.ubselabapi.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.ubselabapi.domain.Graduate;
import org.ubselabapi.domain.UnderGraduate;
import org.ubselabapi.domain.UndergraduateFiled;
import org.ubselabapi.dto.GraduateDto;
import org.ubselabapi.dto.UnderGraduateStudentDto;
import org.ubselabapi.repository.UnderGraduateRepository;
import org.ubselabapi.repository.UndergraduateFiledRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
public class StudentTest {

    @Autowired
    StudentService studentService;


    @Transactional
    @Test
    void UnderGraduateUpdateTest() throws IOException {

        List<UndergraduateFiled> undergraduateFileds = studentService.findFiledById(1L);

        ArrayList<String> filedList = new ArrayList<>();

        for(int i=0; i< undergraduateFileds.size(); i++){


            filedList.add(undergraduateFileds.get(i).getFiled());
        }

        UnderGraduateStudentDto.UnderGraduateStudentUpdateRequest dto = UnderGraduateStudentDto.UnderGraduateStudentUpdateRequest.builder()
                        .id(1L)
                        .email("amazon7737@gmail.com")
                        .name("김강민")
                        .field(filedList)
                        .build();

        studentService.updateUndergraduate(dto);

        UnderGraduateStudentDto.UnderGraduateStudentResponse result = studentService.findUnderGraduateById(1L);

        Assertions.assertEquals("김강민", result.getName());
        Assertions.assertNotNull(result.getId());
        Assertions.assertNotNull(result.getEmail());
//        Assertions.assertNotNull(result.getProfile());
//        Assertions.assertNotNull(result.getField());


    }

    @Test
    @Transactional
    void UnderGraduateFieldUpdateTest(){

        List<UndergraduateFiled> undergraduateFiledList = studentService.findFiledById(1L);

        List<String> fieldInputList = new ArrayList<>();

        fieldInputList.add("머신러닝");
        fieldInputList.add("인공지능");
        fieldInputList.add("데이터베이스");

        studentService.updateUndergraduateField(1L , fieldInputList);

        List<UndergraduateFiled> resultList = studentService.findUnderGraduateFiledById(1L);

//        Assertions.assertEquals(0,resultList.size());
        for(int i=0; i< resultList.size();i++){

            System.out.println(resultList.get(i).getFiled());
        }

        Assertions.assertEquals("머신러닝", resultList.get(0).getFiled());
        Assertions.assertEquals("인공지능", resultList.get(1).getFiled());
        Assertions.assertEquals("데이터베이스", resultList.get(2).getFiled());

    }

    @Test
    @Transactional
    void GraduateUpdateTest(){
        Long grauduated = 2021L;

        GraduateDto dto = GraduateDto.builder()
                        .id(1L)
                        .graduated(grauduated)
                        .build();

        studentService.updateGraduate(dto);

        Graduate result = studentService.findGraudateById(1L);


        Assertions.assertEquals(2021L, result.getGraduated_date());
    }


}
