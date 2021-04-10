package com.servicio.items.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.servicio.items.models.Item;
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

  @GetMapping("/ver/{id}/{cantidad}")
  public Item detalle(@PathVariable Long id, @PathVariable Integer cantidad) {

    return itemService.findById(id, cantidad);
  }
}
