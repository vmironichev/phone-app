package com.smart.phone.catalog;

import com.smart.phone.catalog.resource.PhoneCatalogController;
import com.smart.phone.catalog.resource.model.PhoneModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Configuration
@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@Import(PhoneCatalogController.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PhoneCatalogApplicationTests {

  @LocalServerPort private int port;
  @Autowired private TestRestTemplate restTemplate;
  @Autowired private PhoneCatalogController phoneCatalogController;

	@Test
	public void contextLoads() {
    assertThat(phoneCatalogController).isNotNull();
	}

	@Test
  public void shouldLoadPhoneModels() {
    var url = String.format("http://localhost:%d/phone-catalog/rest/v1/phones", port);
    var headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    List<MediaType> acceptableMediaTypes = new ArrayList<>();
    acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
    headers.setAccept(acceptableMediaTypes);
    var entity = new HttpEntity<>(headers);
    var response = restTemplate.exchange(url, HttpMethod.GET, entity, PhoneModel[].class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    var phoneModelsArray = response.getBody();
    assertThat(phoneModelsArray).isNotEmpty();
    var phoneModel = Optional.ofNullable(phoneModelsArray[0]).orElseThrow();
    assertThat(phoneModel.getCurrency()).isEqualTo("USD");
    assertThat(phoneModel.getDescription()).isNotNull();
    assertThat(phoneModel.getName()).isNotNull();
    assertThat(phoneModel.getId()).isNotNull();
    assertThat(new BigDecimal(phoneModel.getPrice()).compareTo(BigDecimal.ZERO) > 0).isNotNull();
  }

}
