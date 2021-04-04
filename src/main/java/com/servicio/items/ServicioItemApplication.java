package com.servicio.items;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * microservicio al que nos queremos conectar Ribbon: para el balanceo de carga, es decir
 * seleccionar que instanca es la mejor si hay varias
 *
 * @author Pablo
 */
@RibbonClient(name = "servicio-productos")
@EnableFeignClients
@SpringBootApplication
public class ServicioItemApplication {

  public static void main(String[] args) {
    SpringApplication.run(ServicioItemApplication.class, args);
  }
}
