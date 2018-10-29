package com.smart.phone.catalog.infrastructure;

import com.smart.phone.catalog.infrastructure.model.Phone;
import com.smart.phone.catalog.infrastructure.model.PhonesRepository;
import com.smart.phone.catalog.resource.model.PhoneModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by vmironichev on 10/28/18.
 */
@Component
public class PhoneCatalogService {

  @Autowired
  private PhonesRepository phonesRepository;

  public Set<PhoneModel> loadPhoneModels() {
    var phones = phonesRepository.findAll();
    return StreamSupport.stream(phones.spliterator(), false)
            .map(modelToRepresentation())
            .collect(Collectors.toSet());
  }

  public PhoneModel findById(UUID id) {
    var model = phonesRepository.findById(id);
    model.orElseThrow(() ->
            new PhoneCatalogException(String.format("Cant find phone model with id [%s]", id)));
    return modelToRepresentation().apply(model.get());
  }

  private Function<Phone, PhoneModel> modelToRepresentation() {
    return phone -> PhoneModel.builder()
            .currency(phone.getPrice().getCurrency().getCurrencyCode())
            .price(phone.getPrice().getAmount().toString())
            .description(phone.getDescription())
            .imageUrl(phone.getImageUrl())
            .name(phone.getName())
            .id(phone.getUuid())
            .build();
  }

}
