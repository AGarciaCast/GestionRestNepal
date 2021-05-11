package app.dao;

import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.Connection;

@Repository
public class FacturacionDAO extends GenericDAO {

    public Double getFacturacionDia(String fecha) throws Exception {
        Double facturacion;
        String query = "SELECT SUM(importe) AS VolumenFacturado FROM pedido WHERE CAST(fecha AS DATE)=? AND anulado=1";

        try (Connection conn = connector.getConnection()) {
            facturacion = queryRunner.query(conn, query, new ScalarHandler<>(), fecha);
        }
        return facturacion == null ? 0.0 : Math.round(facturacion*10.0)/10.0;
    }
}
