package org.ubselabapi.service;

import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ubselabapi.domain.Image;
import org.ubselabapi.domain.Professor;
import org.ubselabapi.domain.ProfessorFiled;
import org.ubselabapi.domain.UploadFile;
import org.ubselabapi.dto.ProfessorProfileDto;
import org.ubselabapi.repository.ImageRepository;
import org.ubselabapi.repository.ProfessorFiledRepository;
import org.ubselabapi.repository.ProfessorRepository;

import javax.swing.text.html.Option;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    private final ProfessorFiledRepository professorFiledRepository;

    private final ImageRepository imageRepository;

    private final FileService fileService;


    @Transactional
    public void createProfessorInfo(ProfessorProfileDto.createProfileRequest request) throws IOException{

        // 이미지 업로드 및 저장
        Long id = fileService.uploadFile(request.getProfile().get(0));

        // 이미지 번호 검색
        Image profile = fileService.findById(id);

        // 교수님 프로필 저장
        Professor professor = Professor.builder()
                .email(request.getEmail())
                .name(request.getName())
                .profile(profile.getId())
                .build();

        professorRepository.save(professor);

        for(int i =0; i<request.getField().size(); i++){

        // 교수님 프로필 설명 저장
        ProfessorFiled professorFiled = ProfessorFiled.builder()
                .professor_id(professor.getId())
                .data(request.getField().get(i))
                .build();

        professorFiledRepository.save(professorFiled);

        }


    }

    public ProfessorProfileDto.selectProfileResponse findByProfessorInfo(){
        Professor professorResult = professorRepository.findById(1L).get();
        List<ProfessorFiled> professorFiledResult = professorFiledRepository.findByProfessorId(professorResult.getId());

        ArrayList<String> FiledList = new ArrayList<>();

         Image profile = fileService.findById(professorResult.getProfile());

        for(int i=0; i<professorFiledResult.size();i++){
            FiledList.add(professorFiledResult.get(i).getData());
        }

        return ProfessorProfileDto.selectProfileResponse.builder()
                .profile(profile.getUrl())
                .email(professorResult.getEmail())
                .name(professorResult.getName())
                .field(FiledList)
                .build();
    }


    @Transactional
    public void  updateProfessorInfo(ProfessorProfileDto.updateProfileRequest profileRequest)  {


        Professor professor = professorRepository.findById(1L).get();

        Professor input = Professor.builder()
                        .email(profileRequest.getEmail())
                        .name(profileRequest.getName())
                        .build();


        professor.updateProfessor(profileRequest.getName(), profileRequest.getEmail());


        updateProfessorField(1L, profileRequest.getField());


    }

    @Transactional
    public List<String> updateProfessorField(Long id , ArrayList<String> list){

//        List<ProfessorFiled> professorFiledList = professorFiledRepository.findByProfessorId(id);


        deleteByProfessorId(id);
        List<String> result = new ArrayList<>();

        for(int i=0; i<list.size();i++) {

            ProfessorFiled professorFiled = ProfessorFiled.builder()
                    .professor_id(1L)
                    .data(list.get(i))
                    .build();

            String data = professorFiledRepository.save(professorFiled).getData();
            result.add(data);
        }

        return result;
    }


    @Transactional
    public void deleteByProfessorId(Long id){
        professorFiledRepository.deleteByProfessor_id(id);
    }


    @Transactional
    public void deleteProfessorInfo(String professorName)  {



        Professor professor = professorRepository.findByName(professorName).get();
        List<ProfessorFiled> professorFiled = professorFiledRepository.findByProfessorId(professor.getId());
        Image image = imageRepository.findById(professor.getProfile()).get();

        professorRepository.deleteById(professor.getId());
        professorFiledRepository.deleteByProfessor_id(professor.getId());
        imageRepository.deleteById(image.getId());

    }


    public Professor findById(){
        return professorRepository.findById(1L).get();
    }


    public ArrayList<ProfessorFiled> findProfessorFiledById(){
        return professorFiledRepository.findByProfessorId(1L);
    }




}
