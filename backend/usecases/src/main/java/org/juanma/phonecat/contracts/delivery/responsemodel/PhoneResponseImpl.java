package org.juanma.phonecat.contracts.delivery.responsemodel;

import org.juanma.phonecat.model.Phone;

/**
 * Created by Juan Manuel Castillo on 26/03/16.
 */
public class PhoneResponseImpl implements PhoneResponse {
  private String id;
  private String name;
  private String snippet;


  public PhoneResponseImpl(Phone phone) {
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
  public String getImageId() {
    return this.id + ".0.jpg";
  }
}
