package app.model.request.empleado.section;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class EmpleadoRequestRol {
    private Integer id_empleado;
    private String usuario;
    private String contrasenya;
    private Integer id_rol;
    private String nombre_rol;
}