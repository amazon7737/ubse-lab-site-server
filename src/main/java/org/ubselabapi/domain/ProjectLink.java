package org.ubselabapi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ProjectLink")
@Getter
@Builder
public class ProjectLink {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, unique = false)
    private Long project_id;

    @Column(nullable = true, length = 45, unique = false)
    private String link;

    @Column(nullable = true,length = 255, unique = false)
    private String type;


}
