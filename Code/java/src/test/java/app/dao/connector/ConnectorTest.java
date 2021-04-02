package app.dao.connector;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockedStatic;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mockStatic;

@RunWith(MockitoJUnitRunner.class)
public class ConnectorTest {

  private Connector connector;

  @Before
  public void setUp() {
    this.connector = new Connector();
  }

  @Test
  public void getConnection_whenCalled_shouldExecuteDriverManagerGetConnectionMethod() {
    try (
      MockedStatic<DriverManager> mockedStatic = mockStatic(DriverManager.class)
    ) {
      this.connector.getConnection();

      mockedStatic.verify(() -> DriverManager.getConnection(Connector.DB_URL));
    }
  }

  @Test
  public void getConnection_whenAnExceptionIsThrown_shouldReturnNull()
    throws Exception {
    try (
      MockedStatic<DriverManager> mockedStatic = mockStatic(DriverManager.class)
    ) {
      mockedStatic
        .when(() -> DriverManager.getConnection(anyString()))
        .thenThrow(SQLException.class);

      Connection output = this.connector.getConnection();

      assertThat(output).isNull();
    }
  }
}
