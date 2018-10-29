package com.vmi.order;

import com.vmi.order.resource.OrderInformationController;
import com.vmi.order.resource.PhoneCatalogRestClient;
import com.vmi.order.resource.model.CustomerDetails;
import com.vmi.order.resource.model.OrderInformation;
import com.vmi.order.resource.model.OrderItem;
import com.vmi.order.resource.model.PhoneModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@Configuration
@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@Import(OrderInformationController.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderApplicationTests {

  private static final UUID PHONE_MODEL_UUID =  UUID.fromString("f31f735a-b967-4068-9d1a-cb5989d96485");

  @LocalServerPort private int port;

  @Autowired private TestRestTemplate restTemplate;
  @Autowired private OrderInformationController orderInformationController;

  @MockBean private PhoneCatalogRestClient phoneCatalogRestClient;
  @MockBean private ApplicationProperties applicationProperties;

  @Test
  public void contextLoads() {
    assertThat(orderInformationController).isNotNull();
  }

  @Test
  public void orderWithInvalidPhoneModelUuidShouldResultWithHttpNotFound() {
    var url = String.format("http://localhost:%d/order-api/rest/v1/order-information", port);
    var body = OrderInformation.builder()
            .customer(CustomerDetails.builder()
                    .firstName("Foo")
                    .lastName("Bar")
                    .email("baz@gmail.com")
                    .build())
            .items(Set.of(OrderItem.builder()
                    .id(UUID.randomUUID())
                    .quantity(2)
                    .build()))
            .build();
    var response = restTemplate.postForEntity(url, body, OrderInformation.class);
    assertThat(response.getBody()).isNotNull();
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
  }

  @Test
  public void orderWithExistingPhoneModelUuidShouldResultSuccess() {

    var model = PhoneModel.builder()
            .id(PHONE_MODEL_UUID)
            .price("650.00")
            .currency("USD")
            .build();
    Mockito.when(phoneCatalogRestClient.findById(ArgumentMatchers.eq(PHONE_MODEL_UUID))).thenReturn(model);

    var url = String.format("http://localhost:%d/order-api/rest/v1/order-information", port);
    var body = OrderInformation.builder()
            .customer(CustomerDetails.builder()
                    .firstName("Foo")
                    .lastName("Bar")
                    .email("baz@gmail.com")
                    .build())
            .items(Set.of(OrderItem.builder()
                    .id(PHONE_MODEL_UUID)
                    .quantity(2)
                    .build()))
            .build();
    var response = restTemplate.postForEntity(url, body, OrderInformation.class);
    var orderInformation = response.getBody();

    assertThat(orderInformation).isNotNull();
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(orderInformation.getTotalPrice()).isEqualTo("1300.00");
    assertThat(orderInformation.getCurrency()).isEqualTo("USD");
  }

}


