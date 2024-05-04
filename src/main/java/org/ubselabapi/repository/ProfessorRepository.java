package org.ubselabapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ubselabapi.domain.Professor;

public interface ProfessorRepository extends JpaRepository<Professor , Long> {

}
