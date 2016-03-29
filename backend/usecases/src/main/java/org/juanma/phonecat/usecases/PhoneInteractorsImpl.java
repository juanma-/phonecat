package org.juanma.phonecat.usecases;

import org.juanma.phonecat.contracts.delivery.PhoneInteractors;
import org.juanma.phonecat.contracts.external.PhoneRepository;
import org.juanma.phonecat.responsemodel.PhoneVOImpl;

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
  public Stream<PhoneVO> findAllPhones() {
    AtomicInteger orderCount = new AtomicInteger(1);

    return phoneRepository.findAllByOrderByCreatedDateDesc()
        .map(phone -> new PhoneVOImpl(orderCount.getAndIncrement(), phone));
  }
}
