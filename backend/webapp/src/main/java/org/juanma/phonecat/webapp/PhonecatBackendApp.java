package org.juanma.phonecat.webapp;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

/**
 * Created by Juan Manuel Castillo on 28/03/16.
 */
public class PhonecatBackendApp extends Application {
  private Set<Object> singletons = new HashSet<>();

  public PhonecatBackendApp() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
    //FIXME: use Reflections project to locate controllers class.
	Class<?> phoneRestControllerClass = Class.forName("org.juanma.phonecat.controllers.PhoneRestController");
	  
    singletons.add(phoneRestControllerClass.newInstance());
  }

  @Override
  public Set<Object> getSingletons() {
    return singletons;
  }
}
