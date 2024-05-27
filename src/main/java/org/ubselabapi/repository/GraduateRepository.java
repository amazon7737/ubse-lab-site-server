package org.ubselabapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.ubselabapi.domain.Graduate;

public interface GraduateRepository extends JpaRepository<Graduate, Long> {

    void deleteByEmail(@Param("email") String email);

}
