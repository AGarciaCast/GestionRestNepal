package app.dao;

import app.model.Cliente;
import app.model.Login;
import app.model.request.empleado.section.EmpleadoRequestRol;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;

@Repository
public class LoginDAO extends GenericDAO{
    /*
    Devuelve un Object:
        - Si es un cliente -> Cliente
        - Si es un empleado -> EmpleadoRequestRol
        - Si no coincide con un cliente ni empleado -> null
     */
	public Object authenticateUser(Login loginBean) throws SQLException {
        String userName = loginBean.getUsername(); //Assign user entered values to temporary variables.
        String password = loginBean.getPassword();

        Object usuario=null;
        String query = "SELECT id_empleado,usuario, e.id_rol AS id_rol, r.nombre AS nombre_rol " +
                "FROM empleado AS e " +
                "JOIN rol AS r ON e.id_rol=r.id_rol " +
                "WHERE e.usuario= ? and e.contrasenya=?;";

        try (Connection conn = connector.getConnection()) {
            usuario = queryRunner.query(conn, query, new BeanHandler<>(EmpleadoRequestRol.class), userName, password);
        }
        if(usuario==null){
            query = "SELECT id_cliente, usuario, direccion " +
                    "FROM cliente " +
                    "WHERE usuario= ? and contrasenya=?;";

            try (Connection conn = connector.getConnection()) {
                usuario = queryRunner.query(conn, query, new BeanHandler<>(Cliente.class), userName, password);
            }

        }

        return usuario;

    }
}
