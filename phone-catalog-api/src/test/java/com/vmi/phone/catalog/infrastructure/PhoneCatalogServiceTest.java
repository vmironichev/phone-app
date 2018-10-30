package com.vmi.phone.catalog.infrastructure;

import com.vmi.phone.catalog.resource.model.PhoneModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;
import java.util.UUID;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

/**
 * Created by vmironichev on 10/30/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PhoneCatalogServiceTest {

  @Autowired
  private PhoneCatalogService phoneCatalogService;

  @Test
  public void shouldLoadAllPhoneModels() {
    var phoneModels = phoneCatalogService.loadPhoneModels();
    assertThat(phoneModels).isNotNull();
    assertThat(phoneModels.size()).isGreaterThan(0);
    assertThat(phoneModels.stream().allMatch(allNotEmpty())).isTrue();
  }

  @Test(expected = PhoneCatalogException.class)
  public void shouldResultWithExceptionIfPhoneNonFound() {
    phoneCatalogService.findById(UUID.randomUUID());
    failBecauseExceptionWasNotThrown(PhoneCatalogException.class);
  }

  @Test
  public void shouldFindPhoneById() {
    var model = phoneCatalogService.loadPhoneModels().stream().findAny().orElseThrow();
    var modelByID = phoneCatalogService.findById(model.getId());
    assertThat(modelByID).isNotNull();
    assertThat(allNotEmpty().test(modelByID)).isTrue();
  }

  private Predicate<PhoneModel> allNotEmpty() {
    return phoneModel ->
            Objects.nonNull(phoneModel.getId()) &&
                    Objects.nonNull(phoneModel.getCurrency()) && "USD".equals(phoneModel.getCurrency()) &&
                    Objects.nonNull(phoneModel.getDescription()) &&
                    Objects.nonNull(phoneModel.getImageUrl()) &&
                    (phoneModel.getImageUrl().startsWith("http")
                            || phoneModel.getImageUrl().startsWith("NA")) &&
                    Objects.nonNull(phoneModel.getPrice()) &&
                    Objects.nonNull(phoneModel.getName());
  }
}