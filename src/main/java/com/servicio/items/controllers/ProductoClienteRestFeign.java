package com.servicio.items.controllers;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.servicio.items.models.Producto;

/**
 * Nombre del servicio al que nos queremos conectar y su url
 *
 * @author Pablo
 */
@FeignClient(name = "servicio-productos", url = "localhost:8001")
public interface ProductoClienteRestFeign {

  /**
   * endpoint al que nos queremos conectar de servicio-productos
   *
   * @return
   */
  @GetMapping("/listar")
  public List<Producto> listar();

  @GetMapping("/ver/{id}")
  public Producto detalle(@PathVariable Long id);
}
