package org.juanma.phonecat.contracts.external;

import org.juanma.phonecat.model.Phone;

import java.util.stream.Stream;

/**
 * Created by Juan Manuel Castillo on 21/03/16.
 */
public interface PhoneRepository {
  Stream<Phone> findAllByOrderByCreatedDateDesc();
}
