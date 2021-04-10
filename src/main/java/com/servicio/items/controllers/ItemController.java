package com.servicio.items.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.servicio.items.models.Item;
import com.servicio.items.models.Producto;
import com.servicio.items.models.service.ItemService;

@RestController
public class ItemController {
  /** Qualifier es para indicar que implementacion usar */
  @Autowired
  @Qualifier("serviceRestTemplate")
  private ItemService itemService;

  @GetMapping("/listar")
  public List<Item> listar() {
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
}
