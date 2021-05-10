package app.dao;

import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Hashtable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PedidoDAOTest extends GenericDaoTest{

    @Autowired
    private PedidoDAO pedidoDAO;

    @Test
    //Test para comprobar que la funcion crearNuevoPedido se ejecuta correctamente
    //debera ejecutar un insert, y un batch con los parametros adecuados
    public void crearNuevoPedido_whenCalled_shouldExecuteRunnerInsert_shouldExecuteBatchWithCorrectParams()
            throws Exception {
        when(queryRunner.insert(any(), anyString(), any(), anyString()))
                .thenReturn(BigInteger.valueOf(25L));
        //parametros a pasar
        Hashtable<Integer, Integer> platosPedidos = new Hashtable<>();
        platosPedidos.put(1,2);
        platosPedidos.put(2,3);
        pedidoDAO.crearNuevoPedido(platosPedidos, "mi casa", -1);

        //parametros esperados en la query3
        Object[][] expectedBatchParams = {{2, BigInteger.valueOf(25L), 3}, {1, BigInteger.valueOf(25L), 2}};

        //verificar querys
        verify(queryRunner)
                .insert(eq(connection), anyString(),any(ScalarHandler.class), eq("mi casa"));

        verify(queryRunner)
                .batch(eq(connection), anyString(),eq(expectedBatchParams));
    }

    @Test(expected = SQLException.class)
    public void crearNuevoMenu_whenAnExceptionHappensInsert_shouldThrowIt()
            throws Exception {
        when(queryRunner.insert(any(), anyString(), any(), anyString()))
                .thenReturn(BigInteger.valueOf(25L));
        when(queryRunner.insert(any(Connection.class), anyString(),any(), anyString()))
                .thenThrow(SQLException.class);
        //parametros a pasar
        Hashtable<Integer, Integer> platosPedidos = new Hashtable<>();
        platosPedidos.put(1,2);
        platosPedidos.put(2,3);
        pedidoDAO.crearNuevoPedido(platosPedidos, "mi casa", -1);
    }

    @Test(expected = SQLException.class)
    public void crearNuevoMenu_whenAnExceptionHappensBatch_shouldThrowIt()
            throws Exception {
        when(queryRunner.insert(any(), anyString(), any(), anyString()))
                .thenReturn(BigInteger.valueOf(25L));
        when(queryRunner.batch(any(Connection.class), anyString(),any()))
                .thenThrow(SQLException.class);
        //parametros a pasar
        Hashtable<Integer, Integer> platosPedidos = new Hashtable<>();
        platosPedidos.put(1,2);
        platosPedidos.put(2,3);
        pedidoDAO.crearNuevoPedido(platosPedidos, "mi casa", -1);
    }

    @Test
    public void crearNuevoMenu_whenCalled_shouldRetrieveGeneratedId()
            throws Exception {
        when(queryRunner.insert(any(), anyString(), any(), anyString()))
                .thenReturn(BigInteger.valueOf(25L));
        //parametros a pasar
        Hashtable<Integer, Integer> platosPedidos = new Hashtable<>();
        platosPedidos.put(1,2);
        platosPedidos.put(2,3);
        assertThat(pedidoDAO.crearNuevoPedido(platosPedidos, "mi casa", -1)).isEqualTo(25);
    }

}
