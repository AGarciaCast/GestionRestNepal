package app.model.request.plato.section;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class PlatoRequestCategoria {
	
	  private Integer id_plato;
	  private String nombre_plato;
	  private String descripcion_plato;
	  private Float precio;
	  private Integer num_plato;
	  private Integer id_categoria;
	  private String nombre_categoria;
	  private String descripcion_categoria;

}