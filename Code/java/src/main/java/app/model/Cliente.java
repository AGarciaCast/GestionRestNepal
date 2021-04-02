package app.model;

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
public class Cliente {
	  private Integer id_cliente;
	  private String usuario;
	  private String contrasenya;
	  private String direccion;
	  private Float gasto_total;
}
