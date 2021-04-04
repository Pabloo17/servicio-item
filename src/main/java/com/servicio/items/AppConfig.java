package com.servicio.items;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class AppConfig {

  /**
   * Cliente http para poder acceder a recursos que estan en otros microservicios
   *
   * @return
   */
  @Bean("clienteRest")
  public RestTemplate registarRestTemplate() {
    return new RestTemplate();
  }

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.servicio.items.controllers"))
        .paths(PathSelectors.any())
        .build();
  }
}
