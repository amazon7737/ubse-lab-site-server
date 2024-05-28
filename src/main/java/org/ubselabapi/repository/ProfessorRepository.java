package org.ubselabapi.repository;

import org.assertj.core.api.OptionalAssert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.ubselabapi.domain.Professor;

import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor , Long> {

    void deleteByName(@Param("name") String name);

    Optional<Professor> findByName(@Param("name") String name);

}
