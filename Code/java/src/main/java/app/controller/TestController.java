package app.controller;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;

import app.dao.*;
import app.model.*;
import app.model.request.empleado.section.EmpleadoRequestRol;
import app.model.request.plato.section.PlatoRequestPedido;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import app.model.request.plato.section.PlatoRequestCategoria;

@Controller
public class TestController {

	private ArrayList<Integer> selecciones =new ArrayList<Integer>();

	@Autowired
	private PlatoDAO p;

	@Autowired
	private FacturacionDAO f;

	@Autowired
	private LoginDAO l;

	@Autowired
	private CategoriaDAO c;

	@Autowired
	private MenuDAO m;

	@Autowired
	private PedidoDAO pedidoDAO;
	
	@GetMapping("/home")
    public String mensaje(Model model) throws Exception {

		LinkedHashMap<String,List<Plato>> platosPorCategoria= new LinkedHashMap<>();
		List<Plato> platos= new ArrayList<Plato>();

		for (Categoria categoria:c.getCategorias())
		{
			//pedir platos en funcción la categoría
			platos= p.getPlatoCartaCategoria(categoria.getId_categoria());
			if(platos.size() > 0)
				platosPorCategoria.put(categoria.getNombre(),platos);
		}
		model.addAttribute("Carta",platosPorCategoria);
		return "home";
    }

    @PostMapping("/hacerMenu")
	public String gestionarSeleccionesCarta(@RequestParam("seleccion") int[] elegidos, Model model) throws Exception {

		List<Integer> ids= new ArrayList<>();

		for (int seleccion :elegidos) {
				ids.add(seleccion);
			}
		m.crearNuevoMenu(ids);
		return  mensaje(model);
	}

	@PostMapping("/hacerPedido")
	public String gestionarSeleccionesCartaPedido(@RequestParam("seleccion") int[] selecciones, Model model) throws Exception {


		for (int seleccion :selecciones) {
			this.selecciones.add((Integer) seleccion);
		}

		return  pedido(model);
	}

	/*
	@GetMapping("/testDAO")
	@ResponseBody
	public void test() throws Exception {
		Hashtable<Integer, Integer> platosPedidos = new Hashtable<>();
		platosPedidos.put(1,7);
		platosPedidos.put(8,3);
		pedidoDAO.modificarPedido(platosPedidos, 2);
	}
	*/

	/*
	@GetMapping("/testDAO")
	@ResponseBody
	public void test() throws Exception {
		pedidoDAO.eliminarPedido(1);
	}
	*/

	/*
	@GetMapping("/testDAO")
	@ResponseBody
	public Object test() throws Exception {
		Hashtable<Integer, Integer> platosPedidos = new Hashtable<>();
		platosPedidos.put(1,2);
		platosPedidos.put(2,3);
		BigInteger res = pedidoDAO.crearNuevoPedido(platosPedidos, "mi casa", 2);

		return res;
	}
	*/


	/*@GetMapping("/testDAO")
	@ResponseBody
	public Object test() throws Exception {
		Login usuario = new Login("Alex", "123");
		Object res = l.authenticateUser(usuario);
		if(res == null)
			System.out.println("NULL");
		else if(res instanceof EmpleadoRequestRol)
			System.out.println("EMPLEADO");
		else if(res instanceof Cliente)
			System.out.println("CLIENTE");

		return res;
	}*/

	@GetMapping("/testDAO")
	@ResponseBody
	public List<PlatoRequestPedido> test() throws Exception {
		return p.getPlatoPedido(10);
	}


	/*
	@GetMapping("/testDAO")
	@ResponseBody
	public double test() throws Exception {
		LocalDateTime fecha = LocalDateTime.of(2021, 5, 9, 12, 0);
		// LocalDateTime fecha = LocalDateTime.now();
		return f.getFacturacionDia(fecha);
	}
	*/
	/*
	@GetMapping("/testDAO")
	@ResponseBody
	public List<Pedido> test() throws Exception {
		return pedidoDAO.getPedidosDelCliente(1);
	}
	*/

	/*
	@GetMapping("/testDAO")
	@ResponseBody
	public List<PlatoRequestCategoria> test() throws Exception {
		return p.getPlatosCarta();
	}
	*/

	/*
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
	*/

	@GetMapping("/menu")
	public String menu(Model model) throws Exception {
		if(!selecciones.isEmpty())
		{
			selecciones.clear();
		}
		List<PlatoRequestCategoria> entrantes = p.getPlatosMenuActualCategoria("Entrantes");
		model.addAttribute("entrantes",entrantes);
		return "menuPP";
	}
	@PostMapping("/menu")
	public String entranteSeleccionado(@RequestParam("seleccion") int seleccion, Model model) throws Exception {
		selecciones.add(seleccion);
		return  menuPS(model);
	}

	@GetMapping("/menuPS")
	public String menuPS(Model model) throws Exception {
		List<PlatoRequestCategoria> segundos= p.getPlatosMenuActualCategoria("segundos");
		model.addAttribute("segundos",segundos);
		return "menuPS";
	}

	@PostMapping("/menuPS")
	public String segundoSeleccionado(@RequestParam("seleccion") int seleccion, Model model) throws Exception {
		selecciones.add(seleccion);
		return  menuB(model);
	}

	@GetMapping("/menuB")
	public String menuB(Model model) throws Exception {
		List<PlatoRequestCategoria> bebidas= p.getPlatosMenuActualCategoria("Bebidas");
		model.addAttribute("bebida",bebidas);
		return "menuB";
	}

	@PostMapping("/menuB")
	public String bebidaSeleccionado(@RequestParam("seleccion") int seleccion, Model model) throws Exception {
		selecciones.add(seleccion);
		return  menuPostre(model);
	}

	@GetMapping("/menuPostre")
	public String menuPostre(Model model) throws Exception {
		List<PlatoRequestCategoria> postres = p.getPlatosMenuActualCategoria("Postre");
		model.addAttribute("postres",postres);
		return "menuPostre";
	}

	@PostMapping("/menuPostre")
	public String postreSeleccionado(@RequestParam("seleccion") int seleccion, Model model) throws Exception {
		selecciones.add(seleccion);
		model.addAttribute("selecciones", selecciones);
		return  pedido(model);
	}

	@GetMapping("/pedido")
	public String pedido( Model model) throws Exception {

		List<Plato> platos=new ArrayList<>();

		for (int id: selecciones) {
			platos.add(p.getInformacionPlato(id));
		}
		System.out.println(platos);
		model.addAttribute("platos",platos);
		return "pedido";
	}
	@PostMapping("/pedido")
	public String crearPedido(@RequestParam("direccion") String direccion,Model model) throws Exception {
		Hashtable <Integer, Integer> t = new Hashtable<>();
		System.out.println(selecciones.size());
		for(int i=0; i<selecciones.size() ; i++){
			System.out.println(selecciones.get(i));
			t.put(selecciones.get(i),1);
		}
		System.out.println("ESTOY HACIENDO EL PEDIDO");
		pedidoDAO.crearNuevoPedido(t,direccion, -1, false);
		selecciones.clear();
		return "redirect:/home";
	}

	@PostMapping("/pedido/{id}")
	public String crearPedidoClienteRegistrado(@PathVariable int id,
			@RequestParam("direccion") String direccion,Model model) throws Exception {
		Hashtable <Integer, Integer> t = new Hashtable<>();
		System.out.println(selecciones.size());
		for(int i=0; i<selecciones.size() ; i++){
			System.out.println(selecciones.get(i));
			t.put(selecciones.get(i),1);
		}
		System.out.println("ESTOY HACIENDO EL PEDIDO");
		pedidoDAO.crearNuevoPedido(t,direccion, id, false);
		selecciones.clear();
		return "redirect:/home";
	}

	@GetMapping("/login")
	public String login(){
		return "login";
	}




	@GetMapping("/gestionarPedidos/{id}")
	public String gestionPedidos (@PathVariable int id,Model model) throws Exception {

		List<Pedido> pedidos=pedidoDAO.getPedidosDelCliente(id);
		model.addAttribute("pedidos",pedidos);
		for(Pedido pedido : pedidos)
		{
			System.out.println(pedido.getId_pedido());
			System.out.println(pedido.getFecha());
			System.out.println(pedido.getHora_entrega());
		}
		return  "gestionarPedidos";

	}


	@PostMapping("/modificarPedido/{id_pedido}/{id_cliente}")
	public String modificarPedido(@PathVariable int id_pedido,
								  @PathVariable int id_cliente,
								  @RequestParam("seleccion") int[] ArrayIds, Model model) throws Exception
	{
		Hashtable <Integer, Integer> t = new Hashtable<>();
		for(int idA : ArrayIds){
			t.put(idA,1);
		}
		System.out.println("modficando pedido");
		pedidoDAO.modificarPedido(t,id_pedido);
		return "redirect:/gestionarPedidos/"+id_cliente;
	}

	@GetMapping("/facturacion")
	public String facturacion(Model model) throws Exception {
		return "gestor";
	}
	@PostMapping("/facturacion")
	public String facturacionSel(Model model, @RequestParam("fecha_inicio")String inicio) throws Exception {
		double costeFactura=f.getFacturacionDia(inicio);
		System.out.println(costeFactura);
		model.addAttribute("costeFactura",costeFactura);
		return "gestorFactura";
	}


}

