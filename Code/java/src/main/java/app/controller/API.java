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
            resp.hacerCLiente();
            resp.setId(cliente.getId_cliente());
            resp.setName(cliente.getUsuario());
            resp.setDireccion(cliente.getDireccion());
            return  ResponseEntity.ok(resp);
        }

		/*
		Aqui es empleado, luego tiene los permisos de cambiar el menu
		 */
        else if(peticion instanceof EmpleadoRequestRol) {
            EmpleadoRequestRol empleado= (EmpleadoRequestRol) peticion;
            resp.setId(empleado.getId_empleado());
            System.out.println(empleado.getNombre_rol());
            if(empleado.getNombre_rol().equals("Gestor")) resp.hacerGestor();
            else resp.hacerEmpleado();
            System.out.println(resp);
            return  ResponseEntity.ok(resp);
        }
		/*
		Nunca deberia llegar hasta aqui, pero por si acaso
		 */
        else
            return ResponseEntity.status(401).build();
    }

    @ResponseBody
    @PostMapping("/borrarPedido/{id}")
    public void borrarPedido(@PathVariable int id, Model model) throws Exception
    {
        try{
            System.out.println("borrar pedido");
            pedidoDAO.eliminarPedido(id);
        }catch (Exception e)
        {
            System.out.println();
            System.out.println("PORFAVOR DIME SI ESTOS PETA");
        }

    }
}
