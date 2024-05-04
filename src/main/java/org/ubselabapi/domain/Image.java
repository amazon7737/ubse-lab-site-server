package org.ubselabapi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Blob;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter // 이거 없으니까 객체 검색이 안된다... setter랑 같이 공부해봐야 될것같다.
@Table(name = "Image")
public class Image {

    @Id
    @GeneratedValue
    @Column(name = "imageId")
    private Long id;


    @Column(nullable = true, length = 255, unique = false)
    private String url;


}
