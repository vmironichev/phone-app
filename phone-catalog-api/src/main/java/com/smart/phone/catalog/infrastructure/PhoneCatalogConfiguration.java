package com.smart.phone.catalog.infrastructure;

import com.smart.phone.catalog.infrastructure.model.Phone;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

/**
 * Created by vmironichev on 10/28/18.
 */
@EnableJpaRepositories(
        basePackages = "com.smart.phone.catalog.infrastructure.model",
        entityManagerFactoryRef = "phoneDbEntityManagerFactoryBean",
        transactionManagerRef = "phoneDbTransactionManager")
@Configuration
public class PhoneCatalogConfiguration {

  @Autowired
  private ApplicationPropertiesConfig appConfig;

  @Bean
  public PlatformTransactionManager phoneDbTransactionManager(
          LocalContainerEntityManagerFactoryBean phoneDbEntityManagerFactoryBean) {
    var transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(phoneDbEntityManagerFactoryBean.getObject());
    return transactionManager;
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean phoneDbEntityManagerFactoryBean(
          DataSource dataSource) {
    var em = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource(dataSource);
    em.setPackagesToScan(Phone.class.getPackage().getName());
    em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
    HashMap<String, Object> properties = new HashMap<>();
    properties.put("hibernate.hbm2ddl.auto", "none");
    properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLInnoDBDialect");
    em.setJpaPropertyMap(properties);
    return em;
  }

  @Bean
  public DataSource dataSource() {
    var config = new HikariConfig();
    config.setJdbcUrl(appConfig.getDbUrl());
    config.setUsername(appConfig.getDbUser());
    config.setPassword(appConfig.getDbPassword());
    return new HikariDataSource(config);
  }

}
