package app.dao;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;
import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlatoDAOTest extends GenericDaoTest{

    @Autowired
    private PlatoDAO platoDAO;

    @Test
    public void getPlatoMenuActual_whenCalled_shouldExecuteQueryRunnerQueryMethod()
            throws Exception {
        platoDAO.getPlatoMenuActual();

        verify(queryRunner)
                .query(eq(connection), anyString(), any(BeanListHandler.class));
    }

    @Test(expected = SQLException.class)
    public void getPlatoMenuActual_whenAnExceptionHappens_shouldThrowIt()
            throws Exception {
        when(queryRunner.query(any(Connection.class), anyString(), any()))
                .thenThrow(SQLException.class);

        platoDAO.getPlatoMenuActual();
    }

    @Test
    public void getPlatosMenuActualCategorial_whenCalled_shouldExecuteQueryRunnerQueryMethod()
            throws Exception {
        String dummyParam = "Entrantes";
        platoDAO.getPlatosMenuActualCategoria(dummyParam);

        verify(queryRunner)
                .query(eq(connection), anyString(), any(BeanListHandler.class));
    }

    @Test(expected = SQLException.class)
    public void getPlatosMenuActualCategoria_whenAnExceptionHappens_shouldThrowIt()
            throws Exception {
        when(queryRunner.query(any(Connection.class), anyString(), any()))
                .thenThrow(SQLException.class);

        String dummyParam = "Entrantes";
        platoDAO.getPlatosMenuActualCategoria(dummyParam);
    }

    @Test
    public void getPlatoCartaCategoria_whenCalled_shouldExecuteQueryRunnerQueryMethod()
            throws Exception {
        int dummyParam = 1;
        platoDAO.getPlatoCartaCategoria(dummyParam);

        verify(queryRunner)
                .query(eq(connection), anyString(), any(BeanListHandler.class), anyInt());
    }

    @Test(expected = SQLException.class)
    public void getPlatoCartaCategoria_whenAnExceptionHappens_shouldThrowIt()
            throws Exception {
        when(queryRunner.query(any(Connection.class), anyString(), any(), anyInt()))
                .thenThrow(SQLException.class);

        int dummyParam = 1;
        platoDAO.getPlatoCartaCategoria(dummyParam);
    }

    //Test para probar getInformacionPlato
    @Test
    public void getInformacionPlato_whenCalled_shouldExecuteQueryRunnerQueryMethod()
            throws Exception {
        int dummyParam = 1;
        platoDAO.getInformacionPlato(dummyParam);

        verify(queryRunner)
                .query(eq(connection), anyString(), any(BeanHandler.class));
    }

    @Test(expected = SQLException.class)
    public void getInformacionPlato_whenAnExceptionHappens_shouldThrowIt()
            throws Exception {
        when(queryRunner.query(any(Connection.class), anyString(), any()))
                .thenThrow(SQLException.class);

        int dummyParam = 1;
        platoDAO.getInformacionPlato(dummyParam);
    }
}