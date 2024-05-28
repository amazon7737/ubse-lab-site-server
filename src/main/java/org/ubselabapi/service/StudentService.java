package org.ubselabapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.ubselabapi.domain.Graduate;
import org.ubselabapi.domain.Image;
import org.ubselabapi.domain.UnderGraduate;
import org.ubselabapi.domain.UndergraduateFiled;
import org.ubselabapi.dto.GraduateDto;
import org.ubselabapi.dto.UnderGraduateStudentDto;
import org.ubselabapi.repository.GraduateRepository;
import org.ubselabapi.repository.UnderGraduateRepository;
import org.ubselabapi.repository.UndergraduateFiledRepository;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.lang.reflect.Array;
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

           ArrayList<String> fieldList = new ArrayList<>();

           for(int j=0; j<undergraduateFileds.size(); j++){


           fieldList.add(undergraduateFileds.get(j).getFiled());




           }

           UnderGraduateStudentDto.UnderGraduateStudentResponse resultUnderGraduate = UnderGraduateStudentDto.UnderGraduateStudentResponse.builder()
                   .name(underGraduates.get(i).getName())
                   .id(underGraduates.get(i).getId())
                   .profile(profile)
                   .email(underGraduates.get(i).getEmail())
                   .field(fieldList)
                   .build();

           result.add(resultUnderGraduate);
       }

       return result;
    }

    public List<UndergraduateFiled> findFiledById(Long id){
        return undergraduateFiledRepository.findByUndergraduateId(id);
    }

    public UnderGraduateStudentDto.UnderGraduateStudentResponse findUnderGraduateById(Long id){
        UnderGraduate underGraduate = underGraduateRepository.findById(id).get();
        List<UndergraduateFiled> undergraduateFiled = undergraduateFiledRepository.findByUndergraduateId(id);


//        ArrayList<String> fieldList = new ArrayList<>();

//        for(int i=0; i< undergraduateFiled.size();i++){
//            fieldList.add(undergraduateFiled.get(i).getFiled());
//        }
//
        UnderGraduateStudentDto.UnderGraduateStudentResponse response = UnderGraduateStudentDto.UnderGraduateStudentResponse.builder()
                .id(id)
                .name(underGraduate.getName())
                .email(underGraduate.getEmail())
                .field(null)
                .profile(null)
                .build();

        return response;

    }

    public List<UndergraduateFiled> findUnderGraduateFiledById(Long id){
        return undergraduateFiledRepository.findByUndergraduateId(id);
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
    public void updateUndergraduate(UnderGraduateStudentDto.UnderGraduateStudentUpdateRequest dto) throws IOException{

        UnderGraduate underGraduate = underGraduateRepository.findById(dto.getId()).get();

        UnderGraduate input = UnderGraduate.builder()
                .id(dto.getId())
                .name(dto.getName())
                .email(dto.getEmail())
                .profile(underGraduate.getProfile())
                .build();

        // save에 내장된 update 활용
        underGraduateRepository.save(input);
//        underGraduateRepository.updateUnderGraduateById(dto.getId());


        updateUndergraduateField(dto.getId(), dto.getField());

    }


    @Transactional
    public void updateUndergraduateField(Long undergrduadateId, List<String> field){

        List<UndergraduateFiled> undergraduateFiled = undergraduateFiledRepository.findByUndergraduateId(undergrduadateId);

        deleteByUndergraduateId(undergrduadateId);

        for(int i=0; i<field.size();i++){

            UndergraduateFiled undergraduateFiled1 = UndergraduateFiled.builder()
                            .undergraduateId(undergrduadateId)
                            .filed(field.get(i))
                            .build();
            undergraduateFiledRepository.save(undergraduateFiled1);
        }

    }

    public void deleteByUndergraduateId(Long id){
        undergraduateFiledRepository.deleteByUndergraduateId(id);
    }


    @Transactional
    public void deleteUndergraduate(Long id)  {

        UnderGraduate underGraduate = underGraduateRepository.findById(id).get();

//        if(!underGraduate.getId().equals(null)){
//            throw new NullPointerException("해당 학생이 존재하지 않습니다.");
//        }

        underGraduateRepository.deleteById(id);
        undergraduateFiledRepository.deleteAllByUndergraduateId(id);
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
    public void updateGraduate(GraduateDto dto){

        Graduate graduate = graduateRepository.findById(dto.getId()).get();

        graduate.updateGraduate(dto.getGraduated(),dto.getName(), dto.getEmail(), dto.getCompany());

    }


    @Transactional
    public void deleteGraduate(Long id){
        graduateRepository.deleteById(id);
    }


    public Graduate findGraudateById(Long id){
        return graduateRepository.findById(id).get();
    }


}
