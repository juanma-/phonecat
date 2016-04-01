package org.juanma.phonecat.contracts.delivery;

import org.juanma.phonecat.contracts.delivery.responsemodel.PhoneResponse;

import java.util.stream.Stream;

/**
 * Created by Juan Manuel Castillo on 25/03/16.
 */
public interface PhoneInteractors {

  Stream<PhoneResponse> findAllPhonesOrderByNewer();
}
