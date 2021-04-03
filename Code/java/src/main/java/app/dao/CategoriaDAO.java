package app.dao;


import app.model.Categoria;
import app.model.request.plato.section.PlatoRequestCategoria;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoriaDAO extends GenericDAO {


  public List<Categoria> getCategorias() throws Exception {
	    List<Categoria> categorias = new ArrayList<>();
	    String query = "SELECT * FROM categoria;";

	    System.out.println(connector==null);
	    try (Connection conn = connector.getConnection()) {
			categorias =
	        queryRunner.query(conn, query, new BeanListHandler<>(Categoria.class));
	    }

	    return categorias;
	  }
}
