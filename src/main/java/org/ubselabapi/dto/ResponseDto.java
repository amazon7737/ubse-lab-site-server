package org.ubselabapi.dto;

import lombok.*;
import org.ubselabapi.domain.Graduate;

@Data
public class ResponseDto {

    @Data
    @AllArgsConstructor
    public static class Response{

        private int status;

        private Object data;

        private String message;

    }


}
