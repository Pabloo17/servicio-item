package com.servicio.items.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.servicio.commons.models.entity.Producto;
import com.servicio.items.models.Item;
import com.servicio.items.models.service.ItemService;
import lombok.extern.slf4j.Slf4j;

// permite actualizar los componentes que estamos inyectando con value configuraciones y environment
@RefreshScope
@Slf4j
@RestController
public class ItemController {
  /** Qualifier es para indicar que implementacion usar */
  @Autowired
  @Qualifier("serviceFeign")
  private ItemService itemService;

  @Value("${configuracion.texto}")
  private String texto;

  @Autowired private Environment env;

  @GetMapping("/listar")
  public List<Item> listar(
      @RequestParam(name = "nombre", required = false) String nombre,
      @RequestHeader(name = "token-request", required = false) String token) {

    log.info(nombre);
    log.info(token);
    return itemService.findAll();
  }

  @HystrixCommand(fallbackMethod = "metodoAlternativo")
  @GetMapping("/ver/{id}/{cantidad}")
  public Item detalle(@PathVariable Long id, @PathVariable Integer cantidad) {

    return itemService.findById(id, cantidad);
  }

  public Item metodoAlternativo(Long id, Integer cantidad) {
    Item item = new Item();
    Producto producto = new Producto();

    item.setCantidad(cantidad);
    producto.setId(id);
    producto.setNombre("Camara Sony");
    producto.setPrecio(500.00);
    item.setProducto(producto);

    return item;
  }

  @PostMapping("/crear")
  @ResponseStatus(HttpStatus.CREATED)
  public Producto crear(@RequestBody Producto producto) {
    return itemService.save(producto);
  }

  @PutMapping("/editar/{id}")
  @ResponseStatus(HttpStatus.CREATED)
  public Producto editar(@RequestBody Producto producto, @PathVariable Long id) {
    return itemService.update(producto, id);
  }

  @PutMapping("/eliminar/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void eliminar(@RequestBody Producto producto, @PathVariable Long id) {
    itemService.delete(id);
  }

  @GetMapping("/obtener-config")
  public ResponseEntity<Map<String, String>> obtenerConfig(@Value("${server.port}") String puerto) {

    log.info(texto);
    Map<String, String> json = new HashMap<>();
    json.put("texto", texto);
    json.put("puerto", puerto);

    if (env.getActiveProfiles().length > 0 && env.getActiveProfiles()[0].equalsIgnoreCase("dev")) {

      json.put("autor.nombre", env.getProperty("configuracion.autor.nombre"));
      json.put("autor.email", env.getProperty("configuracion.autor.email"));
    }

    return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
  }
}
