package org.ubselabapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.ubselabapi.domain.Graduate;
import org.ubselabapi.domain.UnderGraduate;
import org.ubselabapi.domain.UndergraduateFiled;
import org.ubselabapi.dto.GraduateDto;
import org.ubselabapi.dto.UnderGraduateStudentDto;
import org.ubselabapi.repository.GraduateRepository;
import org.ubselabapi.repository.UnderGraduateRepository;
import org.ubselabapi.repository.UndergraduateFiledRepository;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentService {

    private  final UnderGraduateRepository underGraduateRepository;

    private final UndergraduateFiledRepository undergraduateFiledRepository;

    private final GraduateRepository graduateRepository;

    private final FileService fileService;


    public ArrayList<Object> findUndergraudates(){

        ArrayList<Object> result = new ArrayList();
       List<UnderGraduate> underGraduates = underGraduateRepository.findAll();

       for(int i=0;i<underGraduates.size();i++){

           String profile = fileService.findById(underGraduates.get(i).getProfile()).getUrl();

           List<UndergraduateFiled> undergraduateFileds =undergraduateFiledRepository.findByUndergraduateId(underGraduates.get(i).getId());

           for(int j=0; j<undergraduateFileds.size(); j++){

           ArrayList<String> fieldList = new ArrayList<>();

           fieldList.add(undergraduateFileds.get(j).getFiled());

           UnderGraduateStudentDto.UnderGraduateStudentResponse resultUnderGraduate = UnderGraduateStudentDto.UnderGraduateStudentResponse.builder()
                   .name(underGraduates.get(i).getName())
                   .profile(profile)
                   .email(underGraduates.get(i).getEmail())
                   .field(fieldList)
                   .build();

            result.add(resultUnderGraduate);

           }

       }

       return result;
    }

    @Transactional
    public void saveUndergraduate(UnderGraduateStudentDto.UnderGraduateStudentCreateRequest dto) throws IOException {


        Long profileId = fileService.uploadFile(dto.getProfile().get(0));

        UnderGraduate student = UnderGraduate.builder()
                .name(dto.getName())
                .profile(profileId)
                .email(dto.getEmail())
                .build();



        Long studentId = underGraduateRepository.save(student).getId();

        for(int i=0 ; i< dto.getField().size(); i++){

            UndergraduateFiled undergraduateFiled = UndergraduateFiled.builder()
                    .undergraduateId(studentId)
                    .filed(dto.getField().get(i))
                    .build();


            undergraduateFiledRepository.save(undergraduateFiled);

        }



    }

    @Transactional
    public UnderGraduate updateUndergraduate(UnderGraduateStudentDto dto) throws IOException{

        Long profileId = fileService.uploadFile(dto.getProfile());

        UnderGraduate student = UnderGraduate.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .profile(profileId)
                .build();


//        List<UndergraduateFiled> undergraduateFiledList = undergraduateFiledRepository.findByUndergraduateId(dto.getId());

        // 분야 수정 및 저장
        for(int i=0; i<dto.getField().size(); i++){

            UndergraduateFiled undergraduateFiled = UndergraduateFiled.builder()
                            .filed(dto.getField().get(i))
                            .undergraduateId(dto.getId())
                            .build();

           undergraduateFiledRepository.save(undergraduateFiled);

        }




        // filed 주입
//        for(int i=0 ;i<undergraduateFiledList.size();i++){
//
//        UndergraduateFiled undergraduateFiled = UndergraduateFiled.builder()
//                .filed(undergraduateFiledList.get(i).getFiled())
//                .undergraduateId(undergraduateFiledList.get(i).getUndergraduateId())
//                .build();
//
//        undergraduateFiledRepository.save(undergraduateFiled);
//
//        }

        UnderGraduate result = underGraduateRepository.save(student);

        return result;

    }


    @Transactional
    public void deleteUndergraduate(String email)  {

        UnderGraduate underGraduate = underGraduateRepository.findByEmail(email);

        if(!underGraduate.getId().equals(null)){
            throw new NullPointerException("해당 학생이 존재하지 않습니다.");
        }

        underGraduateRepository.deleteByEmail(email);
        undergraduateFiledRepository.deleteAllByUndergraduateId(underGraduate.getId());
        fileService.deleteImage(underGraduate.getProfile());

    }



    public List<Graduate> findGraduates(){
        return graduateRepository.findAll();
    }

    @Transactional
    public void saveGraduate(GraduateDto dto){
        Graduate student = Graduate.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .company(dto.getCompany())
                .graduated_date(dto.getGraduated())
                .build();

        graduateRepository.save(student);

    }


    @Transactional
    public Graduate updateGraduate(GraduateDto dto){
        Graduate student = Graduate.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .company(dto.getCompany())
                .graduated_date(dto.getGraduated())
                .build();

        Graduate result = graduateRepository.save(student);

        return result;
    }


    @Transactional
    public void deleteGraduate(String email){
        graduateRepository.deleteByEmail(email);
    }



}
