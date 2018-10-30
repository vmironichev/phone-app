package com.vmi.phone.catalog.resource.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

/**
 * Created by vmironichev on 10/28/18.
 */
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PhoneModel {
  @Getter
  private UUID id;
  @Getter
  private String name;
  @Getter
  private String description;
  @Getter
  private String imageUrl;
  @Setter
  @Getter
  private String price;
  @Getter
  private String currency;
}
