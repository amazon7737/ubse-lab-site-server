package org.ubselabapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.ubselabapi.domain.Professor;
import org.ubselabapi.domain.Project;
import org.ubselabapi.domain.project_type;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("select m from Project m where m.project_type = :project_type")
    List<Project> findByProjectType(@Param("project_type") project_type projectType);



}
