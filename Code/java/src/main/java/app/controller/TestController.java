package app.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import app.dao.CategoriaDAO;
import app.dao.MenuDAO;
import app.model.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import app.dao.PlatoDAO;
import app.model.Plato;
import app.model.request.plato.section.PlatoRequestCategoria;


@Controller
public class TestController {
	
	@Autowired
	private PlatoDAO p;

	@Autowired
	private CategoriaDAO c;

	@Autowired
	private MenuDAO m;
	
	@GetMapping("/home")
    public String mensaje(Model model) throws Exception {
		String[] platos= {"PLato1", "Plato2", "Plato3"};
		String[] categorias= {"entrantes", "principales"};
		LinkedHashMap<String,List<Integer>> platosPorCategoria= new LinkedHashMap<>();
		List<Integer> prueba= new ArrayList<>();
		prueba.add(1);
		platosPorCategoria.put("prueba",prueba);
		model.addAttribute("mapa",platosPorCategoria);
		//List<Plato> platos= p.getPlatoMenuActual();
		for (Categoria categoria:c.getCategorias()
			 ) {
			System.out.println(categoria.getNombre());
			System.out.println(categoria.getId_categoria());

		}
        model.addAttribute("platos",platos);
        model.addAttribute("categorias", categorias);
		return "home";
    }

    @PostMapping("/home")
	public String gestionarSeleccionesCarta(@RequestParam("seleccion") int[] selecciones, Model model) throws Exception {

		List<Integer> idsPlatos= new ArrayList<Integer>();

		for (int seleccion :selecciones) {
				idsPlatos.add((Integer) seleccion);
			}
		//m.crearNuevoMenu(idsPlatos);
		return  mensaje(model);
	}

    @GetMapping("/testDAO")
    @ResponseBody
	public List<PlatoRequestCategoria> test() throws Exception {
		List<Integer> miLista = new ArrayList<Integer>();
		miLista.add(1);
		miLista.add(2);
		miLista.add(3);
		m.crearNuevoMenu(miLista);
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


	@GetMapping("/login")
	public String login(){
		return "login";
	}

	@PostMapping("/login")
	public String login(Model m, @RequestParam("username")String nombre, @RequestParam("password")String password) throws Exception{
		m.addAttribute("session",true);
		return mensaje(m);
	}

}

