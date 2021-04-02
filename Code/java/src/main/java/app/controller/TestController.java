package app.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import app.dao.PlatoDAO;
import app.model.request.plato.section.PlatoRequestCategoria;


@Controller
public class TestController {

	@GetMapping("/home")
    public String mensaje() {
    
			
        return "home";
    }
	
}
