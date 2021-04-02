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
	
	  /*public PlatoRequestCategoria(Integer id_plato, String nombre_plato, String descripcion_plato, Float precio,
			Integer num_plato, Integer id_categoria, String nombre_categoria, String descripcion_categoria) {
		super();
		this.id_plato = id_plato;
		this.nombre_plato = nombre_plato;
		this.descripcion_plato = descripcion_plato;
		this.precio = precio;
		this.num_plato = num_plato;
		this.id_categoria = id_categoria;
		this.nombre_categoria = nombre_categoria;
		this.descripcion_categoria = descripcion_categoria;
	}
	
	@Override
	public String toString() {
		return "PlatoRequestCategoria [id_plato=" + id_plato + ", nombre_plato=" + nombre_plato + ", descripcion_plato="
				+ descripcion_plato + ", precio=" + precio + ", num_plato=" + num_plato + ", id_categoria="
				+ id_categoria + ", nombre_categoria=" + nombre_categoria + ", descripcion_categoria="
				+ descripcion_categoria + "]";
	}
	public Integer getId_plato() {
		return id_plato;
	}
	public void setId_plato(Integer id_plato) {
		this.id_plato = id_plato;
	}
	public String getNombre_plato() {
		return nombre_plato;
	}
	public void setNombre_plato(String nombre_plato) {
		this.nombre_plato = nombre_plato;
	}
	public String getDescripcion_plato() {
		return descripcion_plato;
	}
	public void setDescripcion_plato(String descripcion_plato) {
		this.descripcion_plato = descripcion_plato;
	}
	public Float getPrecio() {
		return precio;
	}
	public void setPrecio(Float precio) {
		this.precio = precio;
	}
	public Integer getNum_plato() {
		return num_plato;
	}
	public void setNum_plato(Integer num_plato) {
		this.num_plato = num_plato;
	}
	public Integer getId_categoria() {
		return id_categoria;
	}
	public void setId_categoria(Integer id_categoria) {
		this.id_categoria = id_categoria;
	}
	public String getNombre_categoria() {
		return nombre_categoria;
	}
	public void setNombre_categoria(String nombre_categoria) {
		this.nombre_categoria = nombre_categoria;
	}
	public String getDescripcion_categoria() {
		return descripcion_categoria;
	}
	public void setDescripcion_categoria(String descripcion_categoria) {
		this.descripcion_categoria = descripcion_categoria;
	}*/
}