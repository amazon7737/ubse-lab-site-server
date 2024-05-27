package org.ubselabapi.domain;

import jakarta.persistence.*;
import lombok.*;

// 학부생 분야
@Entity
@Table(name = "UnderGraduateFiled")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UndergraduateFiled {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, length = 45, unique = false)
    private Long undergraduateId;

    @Column(nullable = true, length = 255, unique = false)
    private String filed;

}
