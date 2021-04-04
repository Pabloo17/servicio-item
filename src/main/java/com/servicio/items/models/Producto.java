package com.servicio.items.models;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Producto {

  /** Se puede crear otro ms con las clases comunes y reurilizarlo */
  private Long id;

  private String nombre;
  private Double precio;
  private LocalDate createAt;
}
