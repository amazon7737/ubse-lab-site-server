package org.ubselabapi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "Graduate")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Graduate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, length = 45, unique = false)
    private Long graduated_date;

    @Column(nullable = true, length = 45 , unique = false)
    private String name;

    @Column(nullable = true, length = 45, unique = false)
    private String email;

    @Column(nullable = true, length = 45, unique = false)
    private String company;

    public void updateGraduate(Long graduated_date , String name, String email, String company){
        this.graduated_date = graduated_date;
        this.name = name;
        this.email = email;
        this.company = company;
    }



}
