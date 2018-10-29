package com.smart.phone.catalog.resource.model;

import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

/**
 * Created by vmironichev on 10/28/18.
 */
@ToString
public class CustomerDetails {
  @Getter
  @Size(min = 2, message = "First name is too short")
  private String firstName;
  @Getter
  @Size(min = 2, message = "Last name is too short")
  private String lastName;
  @Getter
  // Java email validation permitted by RFC 5322
  @Email(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$",
          message = "Not supported email address")
  private String email;
}
