package app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class TestController {

	@GetMapping("/home")
    public String mensaje() {
         
        return "home";
    }
	
}
