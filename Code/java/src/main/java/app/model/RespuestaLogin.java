package app.model;


import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString

public class RespuestaLogin {
    private String categoria;
    private int  id;


    public void hacerEmpleado(){
        this.setCategoria("empleado");
    }
    public void hacerCLiente(){
        this.setCategoria("cliente");
    }
    public void hacerNocliente()
    {
        this.setCategoria("no-registrado");
    }
}
