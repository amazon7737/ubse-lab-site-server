package org.ubselabapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.ubselabapi.domain.Image;
import org.ubselabapi.domain.ProfessorFiled;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {


    @Query("select m from Image m where m.url = :url")
    Image findByImageUrl(@Param("url") String url);


}
