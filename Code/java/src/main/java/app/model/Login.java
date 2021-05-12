package app.model;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Login {
	 private String username;
     private String password;
}
