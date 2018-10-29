package com.smart.phone.catalog.resource;

import com.smart.phone.catalog.infrastructure.PhoneCatalogService;
import com.smart.phone.catalog.resource.model.PhoneModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.UUID;

/**
 * Created by vmironichev on 10/28/18.
 */
@RestController
public class PhoneCatalogController {

  private static final String BASE_RESOURCE_PATH = "phone-catalog/rest/v1/phones";
  private static final String APPLICATION_JSON = "application/json";

  @Autowired
  private PhoneCatalogService phoneCatalogService;

  @GetMapping(path = BASE_RESOURCE_PATH, produces = APPLICATION_JSON)
  public ResponseEntity<Set<PhoneModel>> loadPhoneModels() {
    var models = phoneCatalogService.loadPhoneModels();
    return new ResponseEntity<>(models, HttpStatus.OK);
  }

  @GetMapping(path = BASE_RESOURCE_PATH + "/{id}", produces = APPLICATION_JSON)
  public ResponseEntity<PhoneModel> findById(@PathVariable("id") UUID id) {
    var models = phoneCatalogService.findById(id);
    return new ResponseEntity<>(models, HttpStatus.OK);
  }
}
