package com.servicio.items.models.service;

import java.util.List;
import com.servicio.commons.models.entity.Producto;
import com.servicio.items.models.Item;

public interface ItemService {

  public List<Item> findAll();

  public Item findById(Long id, Integer cantidad);

  public Producto save(Producto producto);

  public Producto update(Producto producto, Long id);

  public void delete(Long id);
}
