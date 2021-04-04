package com.servicio.items.models.service;

import java.util.List;
import com.servicio.items.models.Item;

public interface ItemService {

  public List<Item> findAll();

  public Item findById(Long id, Integer cantidad);
}
