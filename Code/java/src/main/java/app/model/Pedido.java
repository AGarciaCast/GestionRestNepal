package app.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Pedido {
	  private Integer id_pedido;
	  private String direccion;
	  private LocalDateTime fecha;
	  private LocalDateTime hora_entrega;
	  private Float importe;
	  private Integer id_estado;
	  private Byte anulado;
}
