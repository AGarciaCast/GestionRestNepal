package app.dao;

import app.model.Tecnologia;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.util.List;

@Repository
public class TecnologiaDAO extends GenericDAO {

  public List<Tecnologia> getTecnologias() throws Exception {
    try (Connection conn = connector.getConnection()) {
      return queryRunner.query(
        conn,
        "SELECT * FROM Tecnologia",
        new BeanListHandler<>(Tecnologia.class)
      );
    }
  }
}
