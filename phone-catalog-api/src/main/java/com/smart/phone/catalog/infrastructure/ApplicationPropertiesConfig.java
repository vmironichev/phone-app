package com.smart.phone.catalog.infrastructure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by vmironichev on 10/29/18.
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "phone")
public class ApplicationPropertiesConfig {
  private String dbUrl;
  private String dbUser;
  private String dbPassword;
}
