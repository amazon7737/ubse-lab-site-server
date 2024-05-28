package org.ubselabapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.ubselabapi.domain.UnderGraduate;

public interface UnderGraduateRepository extends JpaRepository<UnderGraduate, Long> {


    void deleteByEmail(@Param("email") String email);


    UnderGraduate findByEmail(@Param("email") String email);



}
