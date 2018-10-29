package com.smart.phone.catalog.infrastructure.model;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;

/**
 * Created by vmironichev on 10/28/18.
 */
@Entity
@Table(name = "price")
public class Price {
  @Id
  @Getter
  private UUID uuid;
  @Getter
  private BigDecimal amount;
  @Getter
  @Column(name = "currency_code")
  private Currency currency;
}
