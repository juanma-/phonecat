package org.juanma.phonecat.responsemodel;

import org.juanma.phonecat.contracts.delivery.PhoneInteractors;
import org.juanma.phonecat.model.Phone;

/**
 * Created by Juan Manuel Castillo on 26/03/16.
 */
public class PhoneVOImpl implements PhoneInteractors.PhoneVO {
  private String id;
  private String name;
  private String snippet;
  private int age;


  public PhoneVOImpl(int age, Phone phone) {
    this.age = age;
    this.id = phone.getExternalId();
    this.name = phone.getName();
    this.snippet = phone.getSnippet();
  }

  @Override
  public String getId() {
    return this.id;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public String getSnippet() {
    return this.snippet;
  }

  @Override
  public int getAge() {
    return this.age;
  }
}
