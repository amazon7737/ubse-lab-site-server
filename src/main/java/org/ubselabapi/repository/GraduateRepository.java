package org.ubselabapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ubselabapi.domain.Graduate;

public interface GraduateRepository extends JpaRepository<Graduate, Long> {

}
