package app.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Empleado {

  public enum Categoria {
    developers,
    executives,
  }

  private String matricula;
  private String nombre;
  private String foto_url;
  private Categoria categoria;
}
