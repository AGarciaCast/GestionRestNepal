package app.dao;

import app.dao.connector.Connector;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Before;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class GenericDaoTest {

  @MockBean
  protected Connector connector;

  @MockBean
  protected QueryRunner queryRunner;

  @Mock
  protected Connection connection;

  @Mock
  protected Statement statement;

  @Mock
  protected PreparedStatement preparedStatement;

  @Mock
  protected ResultSet resultSet;

  @Before
  public void setup() throws Exception {
    when(connector.getConnection()).thenReturn(connection);
    when(connection.createStatement()).thenReturn(statement);
    when(connection.prepareStatement(anyString()))
      .thenReturn(preparedStatement);
    when(preparedStatement.executeQuery()).thenReturn(resultSet);
    when(statement.executeQuery(anyString())).thenReturn(resultSet);
  }
}
