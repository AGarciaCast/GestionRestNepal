package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import app.dao.PlatoDAO;
import app.model.Plato;
import app.model.request.plato.section.PlatoRequestCategoria;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class TestController {
	
	@Autowired
	private PlatoDAO p;
	
	@GetMapping("/home")
    public String mensaje() {
        return "home";
    }

    @GetMapping("/testDAO")
    @ResponseBody
	public List<Plato> test() throws Exception {
		return p.getPlatoMenuActual();
	}

	@GetMapping("/menu")
	public String menu() {
		return "menuPP";
	}

	@GetMapping("/menuPS")
	public String menuPS() {
		return "menuPS";
	}

	@GetMapping("/menuPostre")
	public String menuPostre() {
		return "menuPostre";
	}

}
