package app.dao;

import app.model.Busqueda;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BusquedaDAO extends GenericDAO {

  public List<Busqueda> getBusquedas() throws Exception {
    List<Busqueda> busquedas = new ArrayList<>();
    String query = "SELECT * FROM Busqueda";

    try (
      Connection conn = connector.getConnection();
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(query);
    ) {
      while (rs.next()) {
        busquedas.add(
          new Busqueda(
            rs.getInt("id"),
            rs.getString("busqueda"),
            rs.getLong("num_resultados")
          )
        );
      }
    }

    return busquedas;
  }

  public List<Busqueda> getBusquedas2() throws Exception {
    List<Busqueda> busquedas = new ArrayList<>();
    String query = "SELECT * FROM Busqueda";

    try (Connection conn = connector.getConnection()) {
      busquedas =
        queryRunner.query(conn, query, new BeanListHandler<>(Busqueda.class));
    }

    return busquedas;
  }

  public void updateBusquedasResults(List<Busqueda> busquedas)
    throws Exception {
    String query = "UPDATE Busqueda SET num_resultados = ? WHERE id = ?";

    try (
      Connection conn = connector.getConnection();
      PreparedStatement pstmt = conn.prepareStatement(query)
    ) {
      for (Busqueda busqueda : busquedas) {
        pstmt.setLong(1, busqueda.getNum_resultados());
        pstmt.setInt(2, busqueda.getId());
        pstmt.addBatch();
      }

      pstmt.executeBatch();
    }
  }

  public void updateBusquedasResults2(List<Busqueda> busquedas)
    throws Exception {
    String query = "UPDATE Busqueda SET num_resultados = ? WHERE id = ?";

    // It is going to be something like (Object[][]):
    /*
    {
      {10000, 1},
      {22122, 2},
      ...
      {40000, 3}
    }
     */
    Object[][] params = busquedas
      .stream()
      .map(
        busqueda ->
          new Object[] { busqueda.getNum_resultados(), busqueda.getId() }
      )
      .toArray(Object[][]::new);

    try (Connection conn = connector.getConnection()) {
      queryRunner.batch(conn, query, params);
    }
  }
}
