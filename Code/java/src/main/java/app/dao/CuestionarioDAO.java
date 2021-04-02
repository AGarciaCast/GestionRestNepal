package app.dao;

import app.model.Cuestionario;
import app.model.request.cuestionario.section.CuestionarioRequestCargo;
import app.model.request.cuestionario.section.CuestionarioRequestSoftskill;
import app.model.request.cuestionario.section.CuestionarioRequestTecnologia;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.sql.Connection;
import java.util.List;

@Repository
public class CuestionarioDAO extends GenericDAO {

  public Integer insertCuestionario(Cuestionario cuestionario)
    throws Exception {
    BigInteger generatedId = null;
    String query =
      "INSERT INTO Cuestionario " +
      "(comentarios_tecnologias,comentarios_softskills,comentarios_cargos,empleado_id) " +
      "VALUES (?,?,?,?)";

    try (Connection conn = connector.getConnection()) {
      generatedId =
        queryRunner.insert(
          conn,
          query,
          new ScalarHandler<>(),
          cuestionario.getComentarios_tecnologias(),
          cuestionario.getComentarios_softskills(),
          cuestionario.getComentarios_cargos(),
          cuestionario.getEmpleado_id()
        );
    }

    return generatedId.intValue();
  }

  public void updateCuestionario(Cuestionario cuestionario) throws Exception {
    String query =
      "UPDATE Cuestionario SET " +
      "comentarios_tecnologias=?," +
      "comentarios_softskills=?," +
      "comentarios_cargos=? " +
      "WHERE id = ?";

    try (Connection conn = connector.getConnection()) {
      queryRunner.update(
        conn,
        query,
        cuestionario.getComentarios_tecnologias(),
        cuestionario.getComentarios_softskills(),
        cuestionario.getComentarios_cargos(),
        cuestionario.getId()
      );
    }
  }

  public Cuestionario getCuestionario(String matricula) throws Exception {
    String query = "SELECT * FROM Cuestionario WHERE empleado_id = ?";

    try (Connection conn = connector.getConnection()) {
      return queryRunner.query(
        conn,
        query,
        new BeanHandler<>(Cuestionario.class),
        matricula
      );
    }
  }

  public void clearExistingRelations(Integer cuestionarioId) throws Exception {
    String clearTechnologiesQuery =
      "DELETE FROM Cuestionario_Tecnologia WHERE cuestionario_id=?";
    String clearSoftskillsQuery =
      "DELETE FROM Cuestionario_Softskill WHERE cuestionario_id=?";
    String clearCargosQuery =
      "DELETE FROM Cuestionario_Cargo WHERE cuestionario_id=?";

    try (Connection conn = connector.getConnection()) {
      queryRunner.update(conn, clearTechnologiesQuery, cuestionarioId);
      queryRunner.update(conn, clearSoftskillsQuery, cuestionarioId);
      queryRunner.update(conn, clearCargosQuery, cuestionarioId);
    }
  }

  public void insertRelatedTechnologies(
    Integer cuestionarioId,
    List<CuestionarioRequestTecnologia> technologies
  ) throws Exception {
    String query =
      "INSERT INTO Cuestionario_Tecnologia " +
      "(cuestionario_id,tecnologia_id,grado_conocimiento) " +
      "VALUES (?,?,?)";
    Object[][] params = technologies
      .stream()
      .map(
        tech ->
          new Object[] {
            cuestionarioId,
            tech.getId(),
            String.valueOf(tech.getGradoConocimiento()),
          }
      )
      .toArray(Object[][]::new);

    try (Connection conn = connector.getConnection()) {
      queryRunner.batch(conn, query, params);
    }
  }

  public void insertRelatedSoftskills(
    Integer cuestionarioId,
    List<CuestionarioRequestSoftskill> softskills
  ) throws Exception {
    String query =
      "INSERT INTO Cuestionario_Softskill " +
      "(cuestionario_id,softskill_id,rendimiento) " +
      "VALUES (?,?,?)";
    Object[][] params = softskills
      .stream()
      .map(
        softskill ->
          new Object[] {
            cuestionarioId,
            softskill.getId(),
            String.valueOf(softskill.getRendimiento()),
          }
      )
      .toArray(Object[][]::new);

    try (Connection conn = connector.getConnection()) {
      queryRunner.batch(conn, query, params);
    }
  }

  public void insertRelatedCargos(
    Integer cuestionarioId,
    List<CuestionarioRequestCargo> cargos
  ) throws Exception {
    String query =
      "INSERT INTO Cuestionario_Cargo " +
      "(cuestionario_id,cargo_id,preferencia) " +
      "VALUES (?,?,?)";
    Object[][] params = cargos
      .stream()
      .map(
        cargo ->
          new Object[] {
            cuestionarioId,
            cargo.getId(),
            String.valueOf(cargo.getPreferencia()),
          }
      )
      .toArray(Object[][]::new);

    try (Connection conn = connector.getConnection()) {
      queryRunner.batch(conn, query, params);
    }
  }
}
