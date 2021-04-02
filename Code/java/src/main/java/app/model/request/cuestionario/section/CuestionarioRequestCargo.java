package app.model.request.cuestionario.section;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class CuestionarioRequestCargo {

  public enum Preferencia {
    No,
    Puede,
    Si,
  }

  private Integer id;
  private Preferencia preferencia;
}
