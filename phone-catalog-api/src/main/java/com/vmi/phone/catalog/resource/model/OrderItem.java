package com.vmi.phone.catalog.resource.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.UUID;

/**
 * Created by vmironichev on 10/29/18.
 */
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderItem {
  @Getter
  private UUID id;
  @Valid
  @Min(0)
  @Getter
  private int quantity = 1;
  @Getter
  private String price;
  @Getter
  private PhoneModel phone;
}
