package org.ubselabapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.ubselabapi.domain.UndergraduateFiled;

import java.util.List;

public interface UndergraduateFiledRepository extends JpaRepository<UndergraduateFiled, Long> {

    List<UndergraduateFiled> findByUndergraduateId(@Param("undergraduateId") Long undergraudateId);


    void deleteAllByUndergraduateId(@Param("undergraduateId") Long id);

}
