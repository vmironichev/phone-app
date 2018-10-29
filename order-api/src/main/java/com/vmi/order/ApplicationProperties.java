package com.vmi.order;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by vmironichev on 10/29/18.
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "phone")
public class ApplicationProperties {
  private String catalogApiHost;
  private Integer catalogApiPort;
}
