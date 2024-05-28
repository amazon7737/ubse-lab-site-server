package org.ubselabapi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Professor")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 프로필 사진 번호
    @Column(nullable = true, length = 45, unique = false)
    private Long profile;

    @Column(nullable = false, length = 45, unique = true)
    private String name;

    @Column(nullable = true, length = 45, unique = false)
    private String email;

    public void updateProfessor( String name, String email){
        this.name = name;
        this.email = email;
    }

}
