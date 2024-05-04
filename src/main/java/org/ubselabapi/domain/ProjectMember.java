package org.ubselabapi.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.AnyKeyJavaClass;

@Getter @Setter
@Entity
@Table(name = "ProjectMember")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, unique = false)
    private Long project_id;

    @Column(nullable = true, length = 45, unique = false)
    private String name;

    @Column(nullable = true, unique = false)
    private Role role;


}
