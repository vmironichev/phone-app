package com.vmi.order.resource;

import com.vmi.order.resource.model.PhoneModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

/**
 * Created by vmironichev on 10/29/18.
 */
@Builder
@AllArgsConstructor
public class PhoneCatalogRestClient {

  private final RestTemplate restTemplate;
  private final String url;

  public PhoneModel findById(UUID id) {
    return restTemplate.getForObject(url + id, PhoneModel.class);
  }
}
