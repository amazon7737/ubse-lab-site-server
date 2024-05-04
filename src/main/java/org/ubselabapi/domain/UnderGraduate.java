package org.ubselabapi.domain;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "UnderGraduate")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UnderGraduate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, length = 45, unique = false)
    private String name;

    @Column(nullable = true, unique = false)
    private Long profile;

    @Column(nullable = true, length = 45, unique = false)
    private String email;

    @Column(nullable = true, length = 45, unique = false)
    private filed filed;


}
