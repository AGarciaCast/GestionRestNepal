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
    private String name;
    private String direccion;


    public void hacerEmpleado(){
        this.setCategoria("empleado");
    }
    public void hacerGestor(){ System.out.println("HOLAAA");
            this.categoria="gestor";}
    public void hacerCLiente(){ this.setCategoria("cliente"); }

}
