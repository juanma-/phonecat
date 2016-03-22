package org.juanma.phonecat.repositories;

import org.juanma.phonecat.model.Phone;
import org.springframework.data.repository.Repository;

import java.util.stream.Stream;

/**
 * Created by Juan Manuel Castillo on 21/03/16.
 */
public interface PhoneRepository extends org.juanma.phonecat.contracts.external.PhoneRepository,
    Repository<Phone, Long>{
  @Override
  Stream<Phone> findAllByOrderByCreatedDateDesc();
}
