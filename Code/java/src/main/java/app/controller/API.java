package app.controller;


import app.dao.LoginDAO;
import app.model.Cliente;
import app.model.Login;
import app.model.RespuestaLogin;
import app.model.request.empleado.section.EmpleadoRequestRol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import app.dao.*;


@Controller
public class API {

    @Autowired
    private LoginDAO l;

    @Autowired
    private PedidoDAO pedidoDAO;


    @ResponseBody
    @PostMapping("/login")
    public ResponseEntity<RespuestaLogin> login(Model m, @RequestBody Login login) throws Exception{


        RespuestaLogin resp= new RespuestaLogin();
        Object peticion=l.authenticateUser(login);
		/*
		Aqui es null, luego no tiene permisos
		 */
        if (peticion== null) {
            return ResponseEntity.status(401).build();
        }
     	/*
		Aqui es cliente, luego tiene los permisos de cliente registrado
		 */
        else if(peticion instanceof Cliente) {

            Cliente cliente= (Cliente) peticion;
            resp.hacerEmpleado();
            resp.setId(cliente.getId_cliente());
            return  ResponseEntity.ok(resp);
        }

		/*
		Aqui es empleado, luego tiene los permisos de cambiar el menu
		 */
        else if(peticion instanceof EmpleadoRequestRol) {
            EmpleadoRequestRol empleado= (EmpleadoRequestRol) peticion;
            resp.hacerEmpleado();
            resp.setId(empleado.getId_empleado());
            return  ResponseEntity.ok(resp);
        }
		/*
		Nunca deberia llegar hasta aqui, pero por si acaso
		 */
        else
            return ResponseEntity.status(401).build();
    }


    @PostMapping("/borrarPedido/{id}")
    public void borrarPedido(@PathVariable int id, Model model) throws Exception
    {
        System.out.println("borrar pedido");
        pedidoDAO.eliminarPedido(id);
    }
}
