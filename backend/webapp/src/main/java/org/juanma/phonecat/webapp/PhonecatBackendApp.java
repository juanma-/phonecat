package org.juanma.phonecat.webapp;

import org.juanma.phonecat.controllers.PhoneRestController;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Juan Manuel Castillo on 28/03/16.
 */
public class PhonecatBackendApp extends Application {
  private Set<Object> singletons = new HashSet<>();

  public PhonecatBackendApp() {
    singletons.add(new PhoneRestController());
  }

  @Override
  public Set<Object> getSingletons() {
    return singletons;
  }
}
