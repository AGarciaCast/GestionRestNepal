package app.dao;

import app.model.Pedido;
import app.model.request.plato.section.PlatoRequestCategoria;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.sql.Connection;
import java.util.Hashtable;
import java.util.List;
import java.util.ArrayList;

@Repository
public class PedidoDAO  extends GenericDAO{

    public BigInteger crearNuevoPedido(Hashtable<Integer, Integer> platos_pedido, String direccion) throws Exception {
        BigInteger id_pedido=null;
        String query1 = "INSERT INTO pedido (direccion, fecha, hora_entrega, importe, id_estado) " +
                        "VALUES (?, NOW(), NOW() + INTERVAL 30 MINUTE, 0, 1);";
        String query2 = "INSERT INTO plato_pedido (id_plato, id_pedido, cantidad) VALUES (?,?, ?);";

        try (Connection conn = connector.getConnection()) {
            id_pedido= queryRunner.insert(conn,query1,new ScalarHandler<>(), direccion);
            BigInteger finalId_pedido = id_pedido;
            Object[][] params = platos_pedido.entrySet()
                    .stream()
                    .map(
                            e ->
                                    new Object[] {
                                            e.getKey(),
                                            finalId_pedido,
                                            e.getValue()
                                    }
                    )
                    .toArray(Object[][]::new);
            queryRunner.batch(conn, query2, params);
        }
        return id_pedido;
    }

    public List<Pedido> getPedidosDelCliente(int id) throws Exception {
        List<Pedido> pedidos = new ArrayList<Pedido>();
        String query =  "SELECT p.id_pedido, p.direccion, p.fecha, p.hora_entrega, p.importe, p.id_estado " +
                        "FROM pedido as p " +
                        "JOIN cliente_pedido as cp " +
                        "ON p.id_pedido=cp.id_pedido " +
                        "WHERE cp.id_cliente=?";

        try (Connection conn = connector.getConnection()) {
            pedidos = queryRunner.query(conn, query, new BeanListHandler<>(Pedido.class), id);
        }

        return pedidos;
    }


}
