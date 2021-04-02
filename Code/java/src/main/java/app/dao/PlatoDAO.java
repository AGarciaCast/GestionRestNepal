package app.dao;



import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Repository;

import app.dao.connector.Connector;
import app.model.request.plato.section.PlatoRequestCategoria;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PlatoDAO extends GenericDAO {


  public List<PlatoRequestCategoria> getPlatoMenuActual() throws Exception {
	  if (connector == null){
		  connector = new Connector();
	  	  Connection conn = connector.getConnection();
  	  }
	  if(queryRunner == null)
		  queryRunner = new QueryRunner();
	    List<PlatoRequestCategoria> platosMenu = new ArrayList<>();
	    String query = 
	    		"SELECT p.id_plato AS id_plato, p.nombre AS nombre_plato, p.descripcion AS descripcion_plato, "+
	    				"p.precio AS precio, p.num_plato AS num_plato, c.id_categoria AS id_categoria, " +
	    				"c.nombre AS nombre_categoria, c.descripcion AS descripcion_categoria " +
	    		"FROM plato AS p " +
	    		"JOIN plato_menu AS pm ON p.id_plato=pm.id_plato " +
	    		"JOIN menu AS m ON pm.id_menu=m.id_menu " +
	    		"JOIN categoria AS c ON p.id_categoria=c.id_categoria " +
	    		"WHERE m.menu_actual=TRUE;";

	    System.out.println(connector==null);
	    try (Connection conn = connector.getConnection()) {
	    	platosMenu =
	        queryRunner.query(conn, query, new BeanListHandler<>(PlatoRequestCategoria.class));
	    }

	    return platosMenu;
	  }

  
  public static void main(String [] args){
	  PlatoDAO p = new PlatoDAO();
	  List<PlatoRequestCategoria> resultado;
	  try {
		  resultado = p.getPlatoMenuActual();
		  for(PlatoRequestCategoria plato: resultado){
			  System.out.println(plato.toString());
		  }
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
  }
}
