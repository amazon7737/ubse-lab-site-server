package org.ubselabapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.ubselabapi.domain.Image;
import org.ubselabapi.domain.Professor;
import org.ubselabapi.domain.ProfessorFiled;
import org.ubselabapi.dto.ProfessorProfileDto;
import org.ubselabapi.repository.ProfessorFiledRepository;
import org.ubselabapi.repository.ProfessorRepository;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    private final ProfessorFiledRepository professorFiledRepository;

    private final FileService fileService;

    public ProfessorProfileDto.selectProfileRequest findByProfessorInfo(){
        Professor professorResult = professorRepository.findById(1L).get();
        List<ProfessorFiled> professorFiledResult = professorFiledRepository.findByProfessorId(professorResult.getId());

        ArrayList<String> FiledList = new ArrayList<>();

         Image profile = fileService.findById(professorResult.getProfile());

        for(int i=0; i<professorFiledResult.size();i++){
            FiledList.add(professorFiledResult.get(i).getData());
        }

        return ProfessorProfileDto.selectProfileRequest.builder()
                .profile(profile.getUrl())
                .email(professorResult.getEmail())
                .name(professorResult.getName())
                .filedList(FiledList)
                .build();
    }


    public void  updateProfessorInfo(ProfessorProfileDto.updateProfileRequest profileRequest) throws IOException {

        Long profileId = fileService.uploadFile(profileRequest.getFile());

        Professor professor = Professor.builder()
                .profile(profileId)
                .name(profileRequest.getName())
                .email(profileRequest.getEmail())
                .build();

        List<ProfessorFiled> professorFiledList = professorFiledRepository.findAll();

        for(int i=0; i<professorFiledList.size(); i++){

        ProfessorFiled  professorFiled = ProfessorFiled.builder()
                        .professor_filed_id(professorFiledList.get(i).getProfessor_filed_id())
                        .professor_id(1L)
                        .data(profileRequest.getFiledList().get(i))
                        .build();

        professorFiledRepository.save(professorFiled);

        }

        professorRepository.save(professor);


    }

//    public updateProfessorFiledInfo(){
//
//    }




}
