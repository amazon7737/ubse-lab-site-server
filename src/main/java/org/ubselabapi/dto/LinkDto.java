package org.ubselabapi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LinkDto {


    private String type;

    private String url;

}
