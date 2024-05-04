package org.ubselabapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.ubselabapi.domain.ProfessorFiled;
import org.ubselabapi.domain.ProjectMember;

import java.util.List;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Long> {

//    @Query("select m from ProfessorFiled  m where m.professor_id = :professor_id")
//    List<ProfessorFiled> findByProfessorId(@Param("professor_id") Long professor_id);


    @Query("select m from ProjectMember  m where m.project_id = :project_id")
    List<ProjectMember>  findByProjectId(@Param("project_id") Long project_id);
}
