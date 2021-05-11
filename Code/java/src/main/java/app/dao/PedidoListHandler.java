package app.dao;

import app.model.Pedido;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PedidoListHandler extends BeanListHandler<Pedido>{

    public PedidoListHandler() {
        super(Pedido.class);
    }

    @Override
    public List<Pedido> handle(ResultSet rs) throws SQLException {
        List<Pedido> pedidos = new ArrayList<Pedido>();
        while (rs.next()) {
            LocalDateTime fecha = rs.getDate("fecha").toLocalDate().atStartOfDay();
            Pedido p = new Pedido(rs.getInt("id_pedido"), rs.getString("direccion"), fecha, fecha,
                    rs.getFloat("importe"), rs.getInt("id_estado"), rs.getByte("anulado"));
            pedidos.add(p);
        }
        return pedidos;
    }
}

