package app.controller;

import java.util.List;

import app.dao.CategoriaDAO;
import app.model.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import app.dao.PlatoDAO;
import app.model.Plato;
import app.model.request.plato.section.PlatoRequestCategoria;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class TestController {
	
	@Autowired
	private PlatoDAO p;

	@Autowired
	private CategoriaDAO c;
	
	@GetMapping("/home")
    public String mensaje(Model model) throws Exception {
		String[] platos= {"PLato1", "Plato2", "Plato3"};
		//List<Plato> platos= p.getPlatoMenuActual();
        model.addAttribute("platos",platos);
		return "home";
    }

    @GetMapping("/testDAO")
    @ResponseBody
	public List<Categoria> test() throws Exception {
		return c.getCategorias();
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
