package app.dao;



import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Repository;

import app.dao.connector.Connector;
import app.model.Plato;
import app.model.request.plato.section.PlatoRequestCategoria;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PlatoDAO extends GenericDAO {


  public List<PlatoRequestCategoria> getPlatoMenuActual() throws Exception {
	    List<PlatoRequestCategoria> platosMenu = new ArrayList<>();
	    String query = "SELECT p.id_plato AS id_plato, p.nombre AS nombre_plato, p.descripcion AS descripcion_plato, "+
	    				"p.precio AS precio, p.num_plato AS num_plato, c.id_categoria AS id_categoria, " +
	    				"c.nombre AS nombre_categoria, c.descripcion AS descripcion_categoria " +
	    		"FROM plato AS p " +
	    		"JOIN plato_menu AS pm ON p.id_plato=pm.id_plato " +
	    		"JOIN menu AS m ON pm.id_menu=m.id_menu " +
	    		"JOIN categoria AS c ON p.id_categoria=c.id_categoria " +
	    		"WHERE m.menu_actual=TRUE;";

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

	public List<PlatoRequestCategoria> getPlatosMenuActualCategoria(String categoria) throws Exception {
		List<PlatoRequestCategoria> platosMenu = new ArrayList<>();
		String query;
		if(categoria.equals("Entrantes") || categoria.equals("Postre")){
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
					"WHERE m.menu_actual=TRUE and c.nombre NOT IN ('Entrantes','Postre');";
		}

		//System.out.println(connector==null);
		try (Connection conn = connector.getConnection()) {
			platosMenu =
					queryRunner.query(conn, query, new BeanListHandler<>(PlatoRequestCategoria.class));
		}

		return platosMenu;
	}
}
