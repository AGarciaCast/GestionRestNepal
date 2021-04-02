package app.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Cuestionario {

  private Integer id;
  private String comentarios_tecnologias;
  private String comentarios_softskills;
  private String comentarios_cargos;
  private String empleado_id;
}
