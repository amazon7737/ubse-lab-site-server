package org.ubselabapi.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * https://colabear754.tistory.com/99
 * https://velog.io/@jeparkk/Springboot3%EC%97%90-Springdoc-%EB%9D%BC%EC%9D%B4%EB%B8%8C%EB%9F%AC%EB%A6%AC%EB%A1%9C-swagger-%EC%A0%81%EC%9A%A9%ED%95%98%EA%B8%B0
 */


@Configuration
public class SwaggerConfig  {



    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());
    }



    private Info apiInfo(){
        return new Info()
                .title("lab-ubse api")
                .description("lab-ubse 사이트 api docs입니다.")
                .version("1.0.0");
    }



}
