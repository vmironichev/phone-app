package com.vmi.phone.catalog.resource.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

/**
 * Created by vmironichev on 10/28/18.
 */
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderInformation {
  @Valid
  @Getter
  private CustomerDetails customer;
  @Getter
  @Valid
  @NotEmpty
  private Set<OrderItem> items;
  @Getter
  private String totalPrice;
  @Getter
  private String currency;

}
