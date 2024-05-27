package org.ubselabapi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 교수님 설명
 */
@Entity
@Table(name = "ProfessorFiled")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProfessorFiled {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long professor_filed_id;

    @Column(nullable = true, unique = false)
    private Long professor_id;

    @Column(nullable = true, length = 255, unique = false)
    private String data;



}
