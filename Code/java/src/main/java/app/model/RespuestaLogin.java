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
    private String respuesta;


    public void loginCompletado()
    {
        this.setRespuesta("Ok");
    }
    public  void loginFallido()
    {
        this.setRespuesta("Fail");
    }
}
