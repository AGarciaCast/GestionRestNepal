package app.dao;

import static app.model.Empleado.Categoria;

import app.model.Empleado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.stereotype.Repository;

@Repository
public class EmpleadoDAO extends GenericDAO {

  public List<Empleado> getEmpleados() throws SQLException {
    List<Empleado> empleados = List.of();
    String query = "SELECT * FROM Empleado";

    try (Connection conn = connector.getConnection()) {
      empleados =
        queryRunner.query(conn, query, new BeanListHandler<>(Empleado.class));
    }

    return empleados;
  }

  public Empleado getEmpleado(String matricula) throws SQLException {
    Empleado empleado = null;
    String query = "SELECT * FROM Empleado WHERE matricula=?";

    try (
      Connection connection = connector.getConnection();
      PreparedStatement pstmt = connection.prepareStatement(query);
    ) {
      pstmt.setString(1, matricula);

      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          empleado =
            new Empleado(
              rs.getString("matricula"),
              rs.getString("nombre"),
              rs.getString("foto_url"),
              Categoria.valueOf(rs.getString("categoria"))
            );
        }
      }
    }

    return empleado;
  }

  public Empleado getEmpleado2(String matricula) throws SQLException {
    Empleado empleado = null;
    String query = "SELECT * FROM Empleado WHERE matricula=?";

    try (Connection conn = connector.getConnection()) {
      empleado =
        queryRunner.query(
          conn,
          query,
          new BeanHandler<>(Empleado.class),
          matricula
        );
    }

    return empleado;
  }

  public void insertEmpleado(Empleado empleado) throws SQLException {
    String query =
      "INSERT INTO Empleado (matricula,nombre,categoria) VALUES (?,?,?)";

    try (Connection conn = connector.getConnection()) {
      queryRunner.insert(
        conn,
        query,
        new ScalarHandler<>(),
        empleado.getMatricula(),
        empleado.getNombre(),
        String.valueOf(empleado.getCategoria())
      );
    }
  }
}
