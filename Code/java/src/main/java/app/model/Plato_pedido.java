package app.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Plato_pedido {
	private Integer id_plato;
	private Integer id_pedido;
	private Integer cantidad;
}
