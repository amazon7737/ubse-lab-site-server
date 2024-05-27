package org.ubselabapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GraduateDto {

    private Long graduated;

    private String name;

    private String email;

    private String company;


}

