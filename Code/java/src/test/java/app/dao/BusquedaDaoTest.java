package app.dao;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.mockito.Mockito.*;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class BusquedaDaoTest extends GenericDaoTest {
/*
  @Autowired
  private BusquedaDAO busquedaDAO;

  @Test
  public void getBusquedas_whenCalled_shouldGetParamsACorrectNumberOfTimes()
    throws Exception {
    when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);

    busquedaDAO.getBusquedas();

    verify(resultSet, times(2)).getInt("id");
    verify(resultSet, times(2)).getString("busqueda");
    verify(resultSet, times(2)).getLong("num_resultados");
  }

  @Test(expected = SQLException.class)
  public void getBusquedas_whenAnExceptionHappens_shouldThrowIt()
    throws Exception {
    when(resultSet.next()).thenThrow(SQLException.class);

    busquedaDAO.getBusquedas();
  }

  @Test
  public void getBusquedas2_whenCalled_shouldExecuteQueryRunnerQueryMethod()
    throws Exception {
    busquedaDAO.getBusquedas2();

    verify(queryRunner)
      .query(eq(connection), anyString(), any(BeanListHandler.class));
  }

  @Test(expected = SQLException.class)
  public void getBusquedas2_whenAnExceptionHappens_shouldThrowIt()
    throws Exception {
    when(queryRunner.query(any(Connection.class), anyString(), any()))
      .thenThrow(SQLException.class);

    busquedaDAO.getBusquedas2();
  }

  @Test
  public void updateBusquedasResults_whenCalled_shouldSetCorrectParams()
    throws Exception {
    List<Busqueda> dummySearches = List.of(
      new Busqueda(1, "dummy1", 100L),
      new Busqueda(2, "dummy2", 200L),
      new Busqueda(3, "dummy3", 300L)
    );

    busquedaDAO.updateBusquedasResults(dummySearches);

    for (Busqueda dummySearch : dummySearches) {
      verify(preparedStatement).setLong(1, dummySearch.getNum_resultados());
      verify(preparedStatement).setInt(2, dummySearch.getId());
    }
    verify(preparedStatement, times(dummySearches.size())).addBatch();
  }

  @Test(expected = SQLException.class)
  public void updateBusquedasResults_whenAnExceptionHappens_shouldThrowIt()
    throws Exception {
    when(connection.prepareStatement(anyString()))
      .thenThrow(SQLException.class);

    busquedaDAO.updateBusquedasResults(List.of());
  }

  @Test
  public void updateBusquedasResults2_whenCalled_shouldExecuteBatchWithCorrectParams()
    throws Exception {
    List<Busqueda> dummySearches = List.of(
      new Busqueda(1, "test1", 1000L),
      new Busqueda(2, "test2", 2000L)
    );
    Object[][] expectedBatchParams = { { 1000L, 1 }, { 2000L, 2 } };

    busquedaDAO.updateBusquedasResults2(dummySearches);

    verify(queryRunner)
      .batch(eq(connection), anyString(), eq(expectedBatchParams));
  }

  @Test(expected = SQLException.class)
  public void updateBusquedasResults2_whenAnExceptionHappens_shouldThrowIt()
    throws Exception {
    List<Busqueda> dummySearches = List.of(
      new Busqueda(57, "another1", 123L),
      new Busqueda(58, "another2", 456L),
      new Busqueda(59, "another3", 789L),
      new Busqueda(60, "another4", 999L)
    );
    when(queryRunner.batch(any(Connection.class), anyString(), any()))
      .thenThrow(SQLException.class);

    busquedaDAO.updateBusquedasResults2(dummySearches);
  }
*/
}

