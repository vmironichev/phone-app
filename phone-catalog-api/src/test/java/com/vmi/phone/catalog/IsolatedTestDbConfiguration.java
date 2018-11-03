package com.vmi.phone.catalog;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.h2.tools.DeleteDbFiles;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.util.UUID;

/**
 * Created by vmironichev on 11/2/18.
 */
@TestConfiguration
public class IsolatedTestDbConfiguration {

  @Bean
  public DataSource dataSource() {
    var config = new HikariConfig();
    var dbName = UUID.randomUUID().toString();
    config.setJdbcUrl(String.format("jdbc:h2:~/%s", dbName));
    config.setUsername("sa");
    config.setPassword("");
    return new HikariDataSource(config) {
      @Override
      public void close() {
        super.close();
        // delete H2 DB files from user home dir after tests completed
        DeleteDbFiles.execute("~",  dbName, true);
      }
    };
  }

}
