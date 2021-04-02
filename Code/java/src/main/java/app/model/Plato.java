package app.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Plato {
	  private Integer id_plato;
	  private String nombre;
	  private String descripción;
	  private Float precio;
	  private Integer num_plato;
	  private Integer id_categoria;
}
