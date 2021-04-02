package app.model.request.cuestionario;

import app.model.request.cuestionario.section.CuestionarioRequestCargo;
import app.model.request.cuestionario.section.CuestionarioRequestSoftskill;
import app.model.request.cuestionario.section.CuestionarioRequestTecnologia;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class CuestionarioRequest {

  private String nombre;
  private String matricula;

  private List<CuestionarioRequestTecnologia> tecnologias;
  private String comentariosTecnologias;

  private List<CuestionarioRequestSoftskill> softskills;
  private String comentariosSoftskills;

  private List<CuestionarioRequestCargo> cargos;
  private String comentariosCargo;
}
