package app.model.request.cuestionario.section;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class CuestionarioRequestTecnologia {

  public enum GradoConocimiento {
    Nulo,
    Poco,
    Medio,
    Experto,
  }

  private Integer id;
  private GradoConocimiento gradoConocimiento;
}
