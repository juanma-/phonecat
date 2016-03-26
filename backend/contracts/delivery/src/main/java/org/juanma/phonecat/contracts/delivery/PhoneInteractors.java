package org.juanma.phonecat.contracts.delivery;

import java.util.stream.Stream;

/**
 * Created by Juan Manuel Castillo on 25/03/16.
 */
public interface PhoneInteractors {
  interface PhoneVO {
    String getId();

    String getName();

    String getSnippet();

//    int getImageId();

    int getAge();
  }

  Stream<PhoneVO> findAllPhones();
}
