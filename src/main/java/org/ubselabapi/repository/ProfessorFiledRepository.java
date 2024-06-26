package org.ubselabapi.repository;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.ubselabapi.domain.ProfessorFiled;
import org.ubselabapi.domain.Project;
import org.ubselabapi.domain.project_type;

import java.util.ArrayList;
import java.util.List;

public interface ProfessorFiledRepository extends JpaRepository<ProfessorFiled, Long> {

    @Query("select m from ProfessorFiled  m where m.professor_id = :professor_id")
    ArrayList<ProfessorFiled> findByProfessorId(@Param("professor_id") Long professor_id);

    @Modifying
    @Query("delete from ProfessorFiled m where m.professor_id = :professor_id")
    void deleteByProfessor_id(@Param("professor_id") Long professor_id);




}
