package app.dao;

import app.model.Cliente_pedido;
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
    /*
    id_cliente
        - Si es cliente registrado su id
        - Si no esta registrado -> -1
     */
    public BigInteger crearNuevoPedido(Hashtable<Integer, Integer> platos_pedido, String direccion, int id_cliente, boolean menu) throws Exception {
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
        if(id_cliente >= 0){
            String query3 = "INSERT INTO cliente_pedido VALUES (?,?)";
            try (Connection conn = connector.getConnection()) {
                queryRunner.insert(conn, query3, new ScalarHandler<>(), id_cliente, id_pedido);
            }
        }

        if(menu){
            String query4 =  "UPDATE pedido SET importe = 12 WHERE id_pedido= ?;";
            try (Connection conn = connector.getConnection()) {
                queryRunner.update(conn,query4,id_pedido);
            }
        }
        return id_pedido;
    }

    public List<Pedido> getPedidosDelCliente(int id) throws Exception {
        List<Pedido> pedidos = new ArrayList<Pedido>();
        String query =  "SELECT p.id_pedido, p.direccion, p.fecha, p.hora_entrega, p.importe, p.id_estado, p.anulado " +
                        "FROM pedido as p " +
                        "JOIN cliente_pedido as cp " +
                        "ON p.id_pedido=cp.id_pedido " +
                        "WHERE cp.id_cliente=? and p.anulado= 1";

        try (Connection conn = connector.getConnection()) {
            pedidos = queryRunner.query(conn, query, new PedidoListHandler(), id);
        }

        return pedidos;
    }

    //Marca un pedido como anulado
    public void eliminarPedido(int id_pedido) throws Exception {
        String query =  "UPDATE pedido SET anulado = 0 WHERE id_pedido="+id_pedido+";";

        try (Connection conn = connector.getConnection()) {
            queryRunner.update(conn,query);
        }
    }

    //Modificar un pedido
    //Dado el id_pedido_antiguo y los nuevos platos del nuevo pedido, se anula el pedido antiguo
    // y se crea un nuevo pedido con los platos solicitados
    public BigInteger modificarPedido(Hashtable<Integer, Integer> platos_pedido, int id_pedido2) throws Exception {
        int id_cliente;
        String direccion;
        BigInteger id_pedido=null;

        String query1 =  "UPDATE pedido SET anulado = 0 WHERE id_pedido="+id_pedido2+";";
        String query2 =  "SELECT id_cliente FROM cliente_pedido WHERE id_pedido="+id_pedido2+";";
        String query3 =  "SELECT direccion FROM pedido WHERE id_pedido="+id_pedido2+";";
        String query4 = "INSERT INTO pedido (direccion, fecha, hora_entrega, importe, id_estado) " +
                "VALUES (?, NOW(), NOW() + INTERVAL 30 MINUTE, 0, 1);";
        String query5 = "INSERT INTO plato_pedido (id_plato, id_pedido, cantidad) VALUES (?,?, ?);";

        try (Connection conn = connector.getConnection()) {
           id_cliente = queryRunner.query(conn, query2, new ScalarHandler<>());
           direccion = queryRunner.query(conn, query3, new ScalarHandler<>());

            id_pedido= queryRunner.insert(conn,query4,new ScalarHandler<>(), direccion);
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
            queryRunner.batch(conn, query5, params);
            queryRunner.update(conn,query1);
        }
        if(id_cliente >= 0){
            String query6 = "INSERT INTO cliente_pedido VALUES (?,?)";
            try (Connection conn = connector.getConnection()) {
                queryRunner.insert(conn, query6, new ScalarHandler<>(), id_cliente, id_pedido);
            }
        }
        return id_pedido;
    }
}
