package org.juanma.phonecat.usecases;

import java.util.stream.Stream;

import javax.inject.Inject;
import javax.inject.Named;

import org.juanma.phonecat.contracts.delivery.PhoneInteractors;
import org.juanma.phonecat.contracts.delivery.responsemodel.PhoneResponse;
import org.juanma.phonecat.contracts.delivery.responsemodel.PhoneResponseImpl;
import org.juanma.phonecat.contracts.external.PhoneRepository;

/**
 * Created by Juan Manuel Castillo on 25/03/16.
 */
@Named("phoneInteractors")
public class PhoneInteractorsImpl implements PhoneInteractors {
  @Inject
  PhoneRepository phoneRepository;

  @Override
  public Stream<PhoneResponse> findAllPhonesOrderByNewer() {
    return phoneRepository.findAllByOrderByCreatedDateDesc()
    		.map(phone -> new PhoneResponseImpl(phone));    
  }
}
