package com.vmi.phone.catalog;

import com.vmi.phone.catalog.resource.PhoneCatalogController;
import com.vmi.phone.catalog.resource.model.PhoneModel;
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
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Configuration
@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@Import(PhoneCatalogController.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PhoneCatalogApplicationTests {

  private static final String PHONE_MODELS_URL_FORMAT = "http://localhost:%d/phone-catalog/rest/v1/phones";
  private static final String PHONE_MODEL_URL_FORMAT = PHONE_MODELS_URL_FORMAT + "/%s";

  @LocalServerPort private int port;
  @Autowired private TestRestTemplate restTemplate;
  @Autowired private PhoneCatalogController phoneCatalogController;

	@Test
	public void contextLoads() {
    assertThat(phoneCatalogController).isNotNull();
	}

	@Test
  public void shouldLoadPhoneModels() {
    var url = String.format(PHONE_MODELS_URL_FORMAT, port);
    ResponseEntity<PhoneModel[]> response = loadPhoneModels(url);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    var phoneModelsArray = response.getBody();

    assert phoneModelsArray != null;
    assertThat(phoneModelsArray).isNotEmpty();
    var phoneModel = Optional.ofNullable(phoneModelsArray[0]).orElseThrow();

    assertThat(phoneModel.getCurrency()).isEqualTo("USD");
    assertThat(phoneModel.getDescription()).isNotNull();
    assertThat(phoneModel.getName()).isNotNull();
    assertThat(phoneModel.getId()).isNotNull();
    assertThat(new BigDecimal(phoneModel.getPrice()).compareTo(BigDecimal.ZERO) > 0).isNotNull();
  }

  @Test
  public void shouldFindPhoneModelByUuid() {
    var phonesUrl = String.format(PHONE_MODELS_URL_FORMAT, port);
    ResponseEntity<PhoneModel[]> phoneModels = loadPhoneModels(phonesUrl);

    assertThat(phoneModels.getStatusCode()).isEqualTo(HttpStatus.OK);
    var phoneModelsArray = phoneModels.getBody();
    assertThat(phoneModelsArray).isNotNull();

    var apiPhoneModel = Arrays.stream(phoneModelsArray).findAny().orElseThrow();
    var phoneModelUuid = apiPhoneModel.getId();

    var url = String.format(PHONE_MODEL_URL_FORMAT, port, phoneModelUuid);
    ResponseEntity<PhoneModel> response = restTemplate.getForEntity(url, PhoneModel.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    PhoneModel foundByIdModel = response.getBody();
    assertThat(foundByIdModel).isNotNull();
    assertThat(foundByIdModel).isEqualTo(apiPhoneModel);

    assertThat(foundByIdModel.getPrice()).isNotNull();
    assertThat(foundByIdModel.getId()).isNotNull();
    assertThat(foundByIdModel.getName()).isNotNull();
    assertThat(foundByIdModel.getDescription()).isNotNull();
    assertThat(foundByIdModel.getCurrency()).isNotNull();
    assertThat(foundByIdModel.getImageUrl()).isNotNull();
  }

  @Test
  public void requestWithNonExistentPhoneUuidShouldResultWith404() {
    var url = String.format(PHONE_MODEL_URL_FORMAT, port, UUID.randomUUID().toString());
    ResponseEntity<PhoneModel> response = restTemplate.getForEntity(url, PhoneModel.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
  }

  @Test
  public void requestWithInvalidUuidShouldResultWith400() {
    var url = String.format(PHONE_MODEL_URL_FORMAT, port, "1234");
    ResponseEntity<PhoneModel> response = restTemplate.getForEntity(url, PhoneModel.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
  }

  private ResponseEntity<PhoneModel[]> loadPhoneModels(String url) {
    var acceptableMediaTypes = Collections.singletonList(MediaType.APPLICATION_JSON);
    var headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(acceptableMediaTypes);
    var entity = new HttpEntity<>(headers);
    return restTemplate.exchange(url, HttpMethod.GET, entity, PhoneModel[].class);
  }

}
