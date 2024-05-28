package org.ubselabapi.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.ubselabapi.domain.Professor;
import org.ubselabapi.domain.ProfessorFiled;
import org.ubselabapi.dto.ProfessorProfileDto;

import javax.sound.sampled.Port;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ProfessorTest {

    @Autowired
    ProfessorService professorService;


    @Test
    @Transactional
    void ProfessorUpdateTest() throws IOException {
        Professor professor = professorService.findById();
        ArrayList<String> outputList = new ArrayList<>();



        String name = "문미경짱";


        ProfessorProfileDto.updateProfileRequest update = ProfessorProfileDto.updateProfileRequest.builder()
                .email(professor.getEmail())
                .name(name)
                .field(outputList)
                .build();

        professorService.updateProfessorInfo(update);

        Professor result = professorService.findById();

        Assertions.assertEquals("문미경짱", result.getName());
    }

    @Test
    @Transactional
    void ProfessorFieldUpdateTest(){
        ArrayList<String> outputList = new ArrayList<>();

        String text = "안녕하세요 ubse 연구실 교수 문미경입니다.";
        String text2 = "얼마나 잘 되는지 테스트 중입니다.";

        outputList.add(text);
        outputList.add(text2);



        List<String> result = professorService.updateProfessorField(1L, outputList);

        System.out.println(result.get(0));
        System.out.println(result.get(1));


        Assertions.assertEquals(text, result.get(0));
        Assertions.assertEquals(text2, result.get(1));

    }

}
