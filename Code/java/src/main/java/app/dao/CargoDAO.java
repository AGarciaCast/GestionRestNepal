package app.dao;

import app.model.Cargo;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.util.List;

@Repository
public class CargoDAO extends GenericDAO {

  public List<Cargo> getCargos() throws Exception {
    try (Connection conn = connector.getConnection()) {
      return queryRunner.query(
        conn,
        "SELECT * FROM Cargo",
        new BeanListHandler<>(Cargo.class)
      );
    }
  }
}
