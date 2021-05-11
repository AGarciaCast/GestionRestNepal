package app.dao;

import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.sql.Connection;

@Repository
public class FacturacionDAO extends GenericDAO {

    public Double getFacturacionDia(LocalDateTime fecha) throws Exception {
        Double facturacion;
        String query = "SELECT SUM(importe) AS VolumenFacturado FROM pedido WHERE fecha=? AND anulado=1";

        // 1 day off bug!
        // java.sql.Date sqlDate = java.sql.Date.valueOf(fecha.toLocalDate());
        String sqlDate = fecha.getYear() + "-" + fecha.getMonthValue() + "-" + fecha.getDayOfMonth();

        try (Connection conn = connector.getConnection()) {
            facturacion = queryRunner.query(conn, query, new ScalarHandler<>(), sqlDate);
        }
        return facturacion == null ? 0.0 : facturacion;
    }
}
