package com.vmi.phone.catalog.resource;

import com.vmi.phone.catalog.infrastructure.PhoneCatalogService;
import com.vmi.phone.catalog.resource.model.PhoneModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

  @Autowired
  private PhoneCatalogService phoneCatalogService;

  @GetMapping(path = BASE_RESOURCE_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Set<PhoneModel>> loadPhoneModels() {
    var models = phoneCatalogService.loadPhoneModels();
    return new ResponseEntity<>(models, HttpStatus.OK);
  }

  @GetMapping(path = BASE_RESOURCE_PATH + "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<PhoneModel> findById(@PathVariable("id") UUID id) {
    var models = phoneCatalogService.findById(id);
    return new ResponseEntity<>(models, HttpStatus.OK);
  }
}
