package org.ubselabapi.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Project")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long project_id;

    @Column(nullable = true, length = 45, unique = false)
    private Long thumb;

    @Column(nullable = true, length = 45, unique = false)
    private String name;

    @Column(nullable = true, length = 45, unique = false)
    private String start;

    @Column(nullable = true, length = 45, unique = false)
    private String end;

    @Column(nullable = true, length = 45, unique = false)
    private String description;

    @Column(nullable = true, length = 45, unique = false)
    private String leader;

    @Column(nullable = true, length = 255 , unique = false)
    private project_type project_type;




}
