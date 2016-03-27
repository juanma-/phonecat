package org.juanma.phonecat.controllers;

import org.juanma.phonecat.contracts.delivery.PhoneInteractors;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by Juan Manuel Castillo on 27/03/16.
 */
@Named
@Path("/phones")
public class PhoneRestController {
  @Inject
  private PhoneInteractors phoneInteractors;

  @GET
  @Produces("application/json")
  public Collection<PhoneInteractors.PhoneVO> readPhones() {
    return phoneInteractors.findAllPhones().collect(Collectors.toList());
  }
}
