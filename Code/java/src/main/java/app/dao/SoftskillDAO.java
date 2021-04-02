package app.dao;

import app.model.Softskill;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.util.List;

@Repository
public class SoftskillDAO extends GenericDAO {

  public List<Softskill> getSoftskills() throws Exception {
    try (Connection conn = connector.getConnection()) {
      return queryRunner.query(
        conn,
        "SELECT * FROM Softskill",
        new BeanListHandler<>(Softskill.class)
      );
    }
  }
}
