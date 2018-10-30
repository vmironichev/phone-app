package com.vmi.phone.catalog.infrastructure.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Created by vmironichev on 10/28/18.
 */
@Repository
public interface PhonesRepository extends CrudRepository<Phone, UUID> {
}
