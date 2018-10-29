package com.smart.phone.catalog.infrastructure.model;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

/**
 * Created by vmironichev on 10/28/18.
 */
@Entity
@Table(name = "phone")
public class Phone {
  @Id
  @Getter
  private UUID uuid;
  @Getter
  private String name;
  @Getter
  private String description;
  @Getter
  @Column(name = "image_url")
  private String imageUrl;
  @Getter
  @ManyToOne
  private Price price;
}
