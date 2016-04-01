package org.juanma.phonecat.usecases;

import org.juanma.phonecat.contracts.delivery.PhoneInteractors;
import org.juanma.phonecat.contracts.delivery.responsemodel.PhoneResponse;
import org.juanma.phonecat.contracts.external.PhoneRepository;
import org.juanma.phonecat.contracts.delivery.responsemodel.PhoneResponseImpl;

import javax.inject.Inject;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * Created by Juan Manuel Castillo on 25/03/16.
 */
public class PhoneInteractorsImpl implements PhoneInteractors {
  @Inject
  PhoneRepository phoneRepository;

  @Override
  public Stream<PhoneResponse> findAllPhones() {
    AtomicInteger orderCount = new AtomicInteger(1);

    return phoneRepository.findAllByOrderByCreatedDateDesc()
        .map(phone -> new PhoneResponseImpl(orderCount.getAndIncrement(), phone));
  }
}
