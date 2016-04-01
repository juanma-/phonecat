package org.juanma.phonecat.controllers;

import org.juanma.phonecat.contracts.delivery.PhoneInteractors;
import org.juanma.phonecat.contracts.delivery.responsemodel.PhoneResponse;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Created by Juan Manuel Castillo on 27/03/16.
 */
@Named
@Path("/phones")
public class PhoneRestController {
  public static class PhoneRestAdapter {
	private int age;
    private PhoneResponse phone;

    PhoneRestAdapter(int age, PhoneResponse phone) {
      this.age = age;
      this.phone = phone;
    }

    public int getAge() {
      return this.age;
    }

    public String getId() {
      return phone.getId();
    }

    public String getImageUrl() {
      return "img/phones/" + phone.getImageId();
    }

    public String getSnippet() {
      return phone.getSnippet();
    }
  }

  @Inject
  private PhoneInteractors phoneInteractors;

  @GET
  @Produces("application/json")
  public Collection<PhoneRestAdapter> readPhones() {
    AtomicInteger orderCount = new AtomicInteger(1);


    return phoneInteractors.findAllPhonesOrderByNewer()
    	.map(phone -> new PhoneRestAdapter(orderCount.getAndIncrement(), phone))
        .collect(Collectors.toList());
  }
}
