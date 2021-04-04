package app.dao;

import app.model.request.plato.section.PlatoRequestCategoria;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.stereotype.Repository;

import app.dao.connector.Connector;
import app.model.Menu;

import java.math.BigInteger;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


@Repository
public class MenuDAO extends GenericDAO{

    public void crearNuevoMenu(List<Integer> platos) throws Exception {
        BigInteger id_menu=null;
        String query1 = "UPDATE menu\n" +
                "SET menu_actual = 0\n" +
                "WHERE menu_actual=1;";
        String query2 = "INSERT INTO menu (menu_actual)\n" +
                "VALUES (1)";
        String query3;
        Object[][] params = platos
                .stream()
                .map(
                        plato ->
                                new Object[] {
                                        plato.intValue(),
                                }
                                )
                .toArray(Object[][]::new);

        try (Connection conn = connector.getConnection()) {
                    queryRunner.update(conn,query1);
                    id_menu= queryRunner.insert(conn,query2,new ScalarHandler<>());
                    query3 = "INSERT INTO plato_menu (id_plato,id_menu)\n" +
                            "VALUES(?,"+id_menu.intValue()+")";
                    queryRunner.batch(conn, query3, params);
        }
    }

}
