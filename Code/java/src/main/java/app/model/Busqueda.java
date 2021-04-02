package app.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Busqueda {

  private Integer id;
  private String busqueda;
  private Long num_resultados;
}
