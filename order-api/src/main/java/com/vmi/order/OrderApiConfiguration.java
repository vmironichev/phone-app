package com.vmi.order;

import com.vmi.order.resource.PhoneCatalogRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by vmironichev on 10/29/18.
 */
@Configuration
public class OrderApiConfiguration {

  @Autowired
  private ApplicationProperties applicationProperties;

  @Bean
  public PhoneCatalogRestClient phoneCatalogRestClient() {
    var restTemplate = new RestTemplate();
    var host = applicationProperties.getCatalogApiHost();
    var port = applicationProperties.getCatalogApiPort();
    var phoneCatalogUrl = String.format("http://%s:%s/phone-catalog/rest/v1/phones/", host, port);
    return PhoneCatalogRestClient.builder()
            .restTemplate(restTemplate)
            .url(phoneCatalogUrl)
            .build();
  }

}
