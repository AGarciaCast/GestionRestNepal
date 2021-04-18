package app.dao;

import app.dao.MenuDAO;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MenuDAOTest extends GenericDaoTest{

    @Autowired
    private MenuDAO menuDAO;

    @Test
    //Test para comprobar que la funcion crearNuevoMenu se ejecuta correctamente
    //debera ejecutar dos update, y un batch con los parametros adecuados
    public void crearNuevoMenu_whenCalled_shouldExecuteRunnerUpdate_shouldExecuteRunnerInsert_shouldExecuteBatchWithCorrectParams()
            throws Exception {
        when(queryRunner.insert(any(), anyString(), any()))
                .thenReturn(BigInteger.valueOf(25L));
        //parametros a pasar
        List<Integer> miLista = new ArrayList<Integer>();
        miLista.add(1);
        miLista.add(2);
        miLista.add(3);
        //llamar a la funcion
        menuDAO.crearNuevoMenu(miLista);
        //parametros esperados en la query3
        Object[][] expectedBatchParams = { { 1 },{ 2 },{ 3 }};

        //verificar querys
        verify(queryRunner)
                .update(eq(connection), anyString());

        verify(queryRunner)
                .insert(eq(connection), anyString(),any(ScalarHandler.class));

        verify(queryRunner)
                .batch(eq(connection), anyString(),eq(expectedBatchParams));
    }

    //Test para ver si salta exception si se llamara a la query mal.....................
    @Test(expected = SQLException.class)
    public void crearNuevoMenu_whenAnExceptionHappensUpdate_shouldThrowIt()
            throws Exception {
        when(queryRunner.insert(any(), anyString(), any()))
                .thenReturn(BigInteger.valueOf(25L));
        when(queryRunner.update(any(Connection.class), anyString()))
                .thenThrow(SQLException.class);
        //parametros a pasar
        List<Integer> miLista = new ArrayList<Integer>();
        miLista.add(1);
        miLista.add(2);
        miLista.add(3);
        //llamar a la funcion
        menuDAO.crearNuevoMenu(miLista);
    }

    @Test(expected = SQLException.class)
    public void crearNuevoMenu_whenAnExceptionHappensInsert_shouldThrowIt()
            throws Exception {
        when(queryRunner.insert(any(), anyString(), any()))
                .thenReturn(BigInteger.valueOf(25L));
        when(queryRunner.insert(any(Connection.class), anyString(),any()))
                .thenThrow(SQLException.class);
        //parametros a pasar
        List<Integer> miLista = new ArrayList<Integer>();
        miLista.add(1);
        miLista.add(2);
        miLista.add(3);
        //llamar a la funcion
        menuDAO.crearNuevoMenu(miLista);
    }

    @Test(expected = SQLException.class)
    public void crearNuevoMenu_whenAnExceptionHappensBatch_shouldThrowIt()
            throws Exception {
        when(queryRunner.insert(any(), anyString(), any()))
                .thenReturn(BigInteger.valueOf(25L));
        when(queryRunner.batch(any(Connection.class), anyString(),any()))
                .thenThrow(SQLException.class);
        //parametros a pasar
        List<Integer> miLista = new ArrayList<Integer>();
        miLista.add(1);
        miLista.add(2);
        miLista.add(3);
        //llamar a la funcion
        menuDAO.crearNuevoMenu(miLista);
    }

}
