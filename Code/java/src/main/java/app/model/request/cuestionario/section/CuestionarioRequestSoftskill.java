package app.model.request.cuestionario.section;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class CuestionarioRequestSoftskill {

  public enum Rendimiento {
    Mal,
    Regular,
    Bien,
    Excelente,
  }

  private Integer id;
  private Rendimiento rendimiento;
}
