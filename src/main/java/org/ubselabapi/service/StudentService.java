package org.ubselabapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.ubselabapi.domain.Graduate;
import org.ubselabapi.domain.UnderGraduate;
import org.ubselabapi.dto.GraduateDto;
import org.ubselabapi.dto.UnderGraduateStudentDto;
import org.ubselabapi.repository.GraduateRepository;
import org.ubselabapi.repository.UnderGraduateRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private  final UnderGraduateRepository underGraduateRepository;

    private final GraduateRepository graduateRepository;

    private final FileService fileService;

    public ArrayList<Object> findUndergraudates(){

        ArrayList<Object> result = new ArrayList();
       List<UnderGraduate> underGraduates = underGraduateRepository.findAll();

       for(int i=0;i<underGraduates.size();i++){

           String profile = fileService.findById(underGraduates.get(i).getProfile()).getUrl();

           UnderGraduateStudentDto.UnderGraduateStudentResponse resultUnderGraduate = UnderGraduateStudentDto.UnderGraduateStudentResponse.builder()
                   .name(underGraduates.get(i).getName())
                   .profile(profile)
                   .email(underGraduates.get(i).getEmail())
                   .filed(underGraduates.get(i).getFiled())
                   .build();

            result.add(resultUnderGraduate);
       }

       return result;
    }

    public void saveUndergraduate(UnderGraduateStudentDto dto) throws IOException {

        Long profileId = fileService.uploadFile(dto.getProfile());

        UnderGraduate student = UnderGraduate.builder()
                .name(dto.getName())
                .profile(profileId)
                .email(dto.getEmail())
                .filed(dto.getFiled())
                .build();

        underGraduateRepository.save(student);

    }

    public UnderGraduate updateUndergraduate(UnderGraduateStudentDto dto) throws IOException{

        Long profileId = fileService.uploadFile(dto.getProfile());

        UnderGraduate student = UnderGraduate.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .filed(dto.getFiled())
                .profile(profileId)
                .build();

        UnderGraduate result = underGraduateRepository.save(student);

        return result;

    }


    public void deleteUndergraduate(Long studentId){

        Long imageId = underGraduateRepository.findById(studentId).get().getProfile();
        underGraduateRepository.deleteById(studentId);
        fileService.deleteImage(imageId);

    }



    public List<Graduate> findGraduates(){
        return graduateRepository.findAll();
    }

    public void saveGraduate(GraduateDto dto){
        Graduate student = Graduate.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .company(dto.getCompany())
                .graduated_date(dto.getGraudated_date())
                .build();

        graduateRepository.save(student);

    }


    public Graduate updateGraduate(GraduateDto dto){
        Graduate student = Graduate.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .company(dto.getCompany())
                .graduated_date(dto.getGraudated_date())
                .build();

        Graduate result = graduateRepository.save(student);

        return result;
    }


    public void deleteGraduate(Long studentId){
        graduateRepository.deleteById(studentId);
    }



}
