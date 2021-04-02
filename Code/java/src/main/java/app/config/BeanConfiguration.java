package app.config;

import java.util.Set;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

@Configuration
public class BeanConfiguration {

  @Bean
  public QueryRunner queryRunner() {
    return new QueryRunner();
  }



  @Bean
  public AbstractRequestLoggingFilter logFilter() {
    return new CustomLogsFilter();
  }
}
