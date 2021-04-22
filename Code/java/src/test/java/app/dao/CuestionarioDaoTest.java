package app.dao;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class CuestionarioDaoTest extends GenericDaoTest {
/*
  @Autowired
  private CuestionarioDAO cuestionarioDAO;

  @Test
  public void insertCuestionario_whenCalled_shouldExecuteRunnerInsertAndRetrieveGeneratedId()
    throws Exception {
    when(queryRunner.insert(any(), anyString(), any(), any()))
      .thenReturn(BigInteger.valueOf(25L));

    assertThat(
      cuestionarioDAO.insertCuestionario(
        new Cuestionario(null, "uno", "dos", "tres", "z17m051")
      )
    )
      .isEqualTo(25);

    verify(queryRunner)
      .insert(
        eq(connection),
        anyString(),
        any(ScalarHandler.class),
        eq("uno"),
        eq("dos"),
        eq("tres"),
        eq("z17m051")
      );
  }

  @Test
  public void updateCuestionario_whenCalled_shouldExecuteRunnerUpdate()
    throws Exception {
    cuestionarioDAO.updateCuestionario(
      new Cuestionario(21, "1", "2", "3", "x18m0123")
    );

    verify(queryRunner)
      .update(eq(connection), anyString(), eq("1"), eq("2"), eq("3"), eq(21));
  }

  @Test
  public void getCuestionario_whenCalled_shouldExecuteRunnerQuery()
    throws Exception {
    cuestionarioDAO.getCuestionario("z17m051");

    verify(queryRunner)
      .query(
        eq(connection),
        anyString(),
        any(BeanHandler.class),
        eq("z17m051")
      );
  }

  @Test
  public void clearExistingRelations_whenCalled_shouldExecuteRunnerUpdateCorrectNumberOfTimes()
    throws Exception {
    cuestionarioDAO.clearExistingRelations(11);

    verify(queryRunner, times(3)).update(eq(connection), anyString(), eq(11));
  }

  @Test
  public void insertRelatedTechnologies_whenCalled_shouldExecuteBatchWithCorrectParams()
    throws Exception {
    Integer dummyCuestionarioId = 100;
    List<CuestionarioRequestTecnologia> dummyTechs = List.of(
      new CuestionarioRequestTecnologia(1, Medio),
      new CuestionarioRequestTecnologia(2, Experto)
    );
    Object[][] expectedBatchParams = {
      { 100, 1, "Medio" },
      { 100, 2, "Experto" },
    };

    cuestionarioDAO.insertRelatedTechnologies(dummyCuestionarioId, dummyTechs);

    verify(queryRunner)
      .batch(eq(connection), anyString(), eq(expectedBatchParams));
  }

  @Test
  public void insertRelatedSoftskills_whenCalled_shouldExecuteBatchWithCorrectParams()
    throws Exception {
    Integer dummyCuestionarioId = 32;
    List<CuestionarioRequestSoftskill> dummySoftskills = List.of(
      new CuestionarioRequestSoftskill(4, Mal),
      new CuestionarioRequestSoftskill(5, Excelente),
      new CuestionarioRequestSoftskill(6, Bien)
    );
    Object[][] expectedBatchParams = {
      { 32, 4, "Mal" },
      { 32, 5, "Excelente" },
      { 32, 6, "Bien" },
    };

    cuestionarioDAO.insertRelatedSoftskills(
      dummyCuestionarioId,
      dummySoftskills
    );

    verify(queryRunner)
      .batch(eq(connection), anyString(), eq(expectedBatchParams));
  }

  @Test
  public void insertRelatedCargos_whenCalled_shouldExecuteBatchWithCorrectParams()
    throws Exception {
    Integer dummyCuestionarioId = 22;
    List<CuestionarioRequestCargo> dummyCargos = List.of(
      new CuestionarioRequestCargo(77, Si)
    );
    Object[][] expectedBatchParams = { { 22, 77, "Si" } };

    cuestionarioDAO.insertRelatedCargos(dummyCuestionarioId, dummyCargos);

    verify(queryRunner)
      .batch(eq(connection), anyString(), eq(expectedBatchParams));
  }

 */
}
