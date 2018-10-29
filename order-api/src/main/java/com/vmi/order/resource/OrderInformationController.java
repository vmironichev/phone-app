package com.vmi.order.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.vmi.order.ApplicationProperties;
import com.vmi.order.resource.model.OrderInformation;
import com.vmi.order.resource.model.OrderItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by vmironichev on 10/28/18.
 */
@Slf4j
@RestController
public class OrderInformationController {

  @Autowired
  private ApplicationProperties applicationProperties;

  @Autowired
  private PhoneCatalogRestClient phoneCatalogRestClient;

  private static final ObjectMapper objectMapper = new ObjectMapper();
  static {
    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
  }

  @PostMapping(path = "order-api/rest/v1/order-information", consumes = "application/json", produces = "application/json")
  public ResponseEntity<OrderInformation> orderInformation(
          @Valid @RequestBody OrderInformation orderInformation) {

    var models = orderInformation.getItems()
            .stream()
            .map(fetchPhoneCatalogDataMappingFunction())
            .collect(Collectors.toSet());

    var totalPrice = models.stream().map(model -> new BigDecimal(model.getPrice()))
            .reduce(BigDecimal.ZERO, BigDecimal::add)
            .setScale(2, RoundingMode.HALF_UP);

    var currency = models.stream().findAny().orElseThrow().getPhone().getCurrency();
    return new ResponseEntity<>(logOrderInformation(OrderInformation.builder()
            .customer(orderInformation.getCustomer())
            .totalPrice(totalPrice.toString())
            .currency(currency)
            .items(models)
            .build()), HttpStatus.OK);
  }

  private OrderInformation logOrderInformation(OrderInformation orderInfo) {
    try {
      log.info("Requested order information is:\n{}", objectMapper.writeValueAsString(orderInfo));
    } catch (JsonProcessingException e) {
      log.error(e.getMessage(), e);
    }
    return orderInfo;
  }

  private Function<OrderItem, OrderItem> fetchPhoneCatalogDataMappingFunction() {
    return item -> {
      try {
        var phoneModel = phoneCatalogRestClient.findById(item.getId());
        var price = new BigDecimal(phoneModel.getPrice()).setScale(2, RoundingMode.HALF_UP);
        return OrderItem.builder()
                .quantity(item.getQuantity())
                .price(price.multiply(BigDecimal.valueOf(item.getQuantity())).toString())
                .phone(phoneModel)
                .build();
      } catch (HttpClientErrorException e) {
        String message = String.format("Error occurred during requesting phone-catalog API: %s ", e.getMessage());
        throw new IllegalStateException(message, e);
      }
    };
  }
}
