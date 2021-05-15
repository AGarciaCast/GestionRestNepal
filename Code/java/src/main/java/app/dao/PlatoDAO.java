package app.dao;


import app.model.Plato;
import app.model.request.plato.section.PlatoRequestCategoria;
import app.model.request.plato.section.PlatoRequestPedido;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PlatoDAO extends GenericDAO {


  public List<PlatoRequestCategoria> getPlatoMenuActual() throws Exception {
	  List<PlatoRequestCategoria> platosMenu;
	  String query = "SELECT p.id_plato AS id_plato, p.nombre AS nombre_plato, p.descripcion AS descripcion_plato, "+
	    				"p.precio AS precio, p.num_plato AS num_plato, c.id_categoria AS id_categoria, " +
	    				"c.nombre AS nombre_categoria, c.descripcion AS descripcion_categoria " +
	    		"FROM plato AS p " +
	    		"JOIN plato_menu AS pm ON p.id_plato=pm.id_plato " +
	    		"JOIN menu AS m ON pm.id_menu=m.id_menu " +
	    		"JOIN categoria AS c ON p.id_categoria=c.id_categoria " +
	    		"WHERE m.menu_actual=TRUE " +
			    "ORDER BY id_categoria;";

	    //System.out.println(connector==null);
	    try (Connection conn = connector.getConnection()) {
	    	platosMenu =
	        queryRunner.query(conn, query, new BeanListHandler<>(PlatoRequestCategoria.class));
	    }

	    return platosMenu;
  }

	public List<Plato> getPlatoCartaCategoria(int id) throws Exception {
		List<Plato> platos = new ArrayList<>();
		String query = "SELECT p.id_plato AS id_plato, " +
				"p.nombre AS nombre, " +
				"p.descripcion AS descripcion, " +
				"p.precio AS precio, " +
				"p.num_plato AS num_plato, " +
				"p.id_categoria AS id_categoria " +
				"FROM plato AS p " +
				"JOIN plato_carta AS pc ON p.id_plato=pc.id_plato " +
				"JOIN carta AS c ON pc.id_carta=c.id_carta " +
				"JOIN temporada AS t ON c.id_temporada=t.id_temporada " +
				"WHERE p.id_categoria=? and t.nombre='primavera';";

		try (Connection conn = connector.getConnection()) {
			platos = queryRunner.query(conn, query, new BeanListHandler<>(Plato.class), id);
		}

		return platos;
	}

	public List<PlatoRequestCategoria> getPlatosCarta() throws Exception {
		List<PlatoRequestCategoria> platos = new ArrayList<>();
		String query = "SELECT p.id_plato AS id_plato, p.nombre AS nombre_plato, p.descripcion AS descripcion_plato, "+
				"p.precio AS precio, p.num_plato AS num_plato, c.id_categoria AS id_categoria, " +
				"c.nombre AS nombre_categoria, c.descripcion AS descripcion_categoria " +
				"FROM plato AS p " +
				"JOIN categoria AS c ON p.id_categoria=c.id_categoria " +
				"JOIN plato_carta AS pc ON p.id_plato=pc.id_plato " +
				"JOIN carta AS ca ON pc.id_carta=ca.id_carta " +
				"JOIN temporada AS t ON ca.id_temporada=t.id_temporada " +
				"WHERE t.nombre='primavera' " +
				"ORDER BY id_categoria";

		try (Connection conn = connector.getConnection()) {
			platos = queryRunner.query(conn, query, new BeanListHandler<>(PlatoRequestCategoria.class));
		}

		return platos;
	}

	public List<PlatoRequestCategoria> getPlatosMenuActualCategoria(String categoria) throws Exception {
		List<PlatoRequestCategoria> platosMenu = new ArrayList<>();
		String query;
		if(categoria.equals("Entrantes") || categoria.equals("Postre") || categoria.equals("Bebidas")){
			 query = "SELECT p.id_plato AS id_plato, p.nombre AS nombre_plato, p.descripcion AS descripcion_plato, "+
					"p.precio AS precio, p.num_plato AS num_plato, c.id_categoria AS id_categoria, " +
					"c.nombre AS nombre_categoria, c.descripcion AS descripcion_categoria " +
					"FROM plato AS p " +
					"JOIN plato_menu AS pm ON p.id_plato=pm.id_plato " +
					"JOIN menu AS m ON pm.id_menu=m.id_menu " +
					"JOIN categoria AS c ON p.id_categoria=c.id_categoria " +
					"WHERE m.menu_actual=TRUE and c.nombre='"+categoria+"';";
		}else{
			query = "SELECT p.id_plato AS id_plato, p.nombre AS nombre_plato, p.descripcion AS descripcion_plato, "+
					"p.precio AS precio, p.num_plato AS num_plato, c.id_categoria AS id_categoria, " +
					"c.nombre AS nombre_categoria, c.descripcion AS descripcion_categoria " +
					"FROM plato AS p " +
					"JOIN plato_menu AS pm ON p.id_plato=pm.id_plato " +
					"JOIN menu AS m ON pm.id_menu=m.id_menu " +
					"JOIN categoria AS c ON p.id_categoria=c.id_categoria " +
					"WHERE m.menu_actual=TRUE and c.nombre NOT IN ('Entrantes','Postre','Bebidas');";
		}

		//System.out.println(connector==null);
		try (Connection conn = connector.getConnection()) {
			platosMenu =
					queryRunner.query(conn, query, new BeanListHandler<>(PlatoRequestCategoria.class));
		}

		return platosMenu;
	}

	//Consulta para obtener la información de un plato dada su id
	public Plato getInformacionPlato (int id_plato) throws SQLException {
  	Plato plato;
  	String query = "SELECT id_plato,nombre,descripcion,precio,num_plato,id_categoria\n" +
			"FROM plato\n" +
			"WHERE id_plato ="+id_plato;
  	try (Connection conn = connector.getConnection()) {
  		plato =
				queryRunner.query(conn, query, new BeanHandler<>(Plato.class));
  	}
  	return plato;
	}

	public List<PlatoRequestPedido> getPlatoPedido(int id_pedido) throws SQLException {
		List<PlatoRequestPedido> platos = new ArrayList<PlatoRequestPedido>();
		String query = "SELECT p.id_plato, p.nombre, p.descripcion, p.precio, p.num_plato, p.id_categoria, pp.cantidad " +
						"FROM plato AS p " +
						"JOIN plato_pedido AS pp " +
						"ON p.id_plato = pp.id_plato " +
						"WHERE pp.id_pedido = ?";
		try (Connection conn = connector.getConnection()) {
			platos = queryRunner.query(conn, query, new BeanListHandler<>(PlatoRequestPedido.class), id_pedido);
		}
		return platos;
	}
}
