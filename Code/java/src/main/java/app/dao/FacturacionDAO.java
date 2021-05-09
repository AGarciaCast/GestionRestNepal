package app.dao;

import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.sql.Connection;

@Repository
public class FacturacionDAO extends GenericDAO {

    public double getFacturacionDia(LocalDateTime fecha) throws Exception {
        double facturacion;
        String query = "SELECT SUM(importe) AS VolumenFacturado FROM pedido WHERE fecha=?";
        try (Connection conn = connector.getConnection()) {
            facturacion = queryRunner.query(conn, query, new ScalarHandler<>(), fecha);
        }
        return facturacion;
    }
}
