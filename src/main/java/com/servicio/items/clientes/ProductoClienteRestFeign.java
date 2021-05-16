package com.servicio.items.clientes;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.servicio.items.models.Producto;

/**
 * Nombre del servicio al que nos queremos conectar y su url
 *
 * @author Pablo
 */
@FeignClient(name = "servicio-productos")
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

  @PostMapping("/crear")
  public Producto crear(@RequestBody Producto producto);

  @PutMapping("/editar/{id}")
  public Producto update(@RequestBody Producto producto, @PathVariable Long id);

  @PutMapping("/eliminar/{id}")
  public void eliminar(@PathVariable Long id);
}
