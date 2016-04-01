package org.juanma.phonecat.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Collections;

import javax.ws.rs.core.Response;

import org.juanma.phonecat.contracts.delivery.PhoneInteractors;
import org.juanma.phonecat.contracts.delivery.responsemodel.PhoneResponse;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by Juan Manuel Castillo on 27/03/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class PhoneRestControllerTest {
  @Mock static PhoneInteractors phoneInteractors;
  @InjectMocks static PhoneRestController phoneRestController = new PhoneRestController();

  public static InMemoryRestServer server;

  @BeforeClass
  public static void beforeClass() throws Exception {
    server = InMemoryRestServer.create(phoneRestController);
  }

  @AfterClass
  public static void afterClass() throws Exception {
    server.close();
  }

  @Test
  public void readPhones_NoPhones_Empty() throws Exception {
    Mockito.when(phoneInteractors.findAllPhonesOrderByNewer())
        .thenReturn(Collections.<PhoneResponse>emptyList().stream());


    Response response = server.newRequest("/phones").request().buildGet().invoke();
    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
//    List<PhoneInteractors.PhoneResponse> phones =
//        response.readEntity(new GenericType<List<PhoneInteractors.PhoneResponse>>() {});
    response.close();
  }

  @Test
  public void readPhones_UniquePhone_OnlyOne() throws Exception {
	Mockito.when(phoneInteractors.findAllPhonesOrderByNewer())
	  	.thenReturn(Collections.<PhoneResponse>emptyList().stream());


	Response response = server.newRequest("/phones").request().buildGet().invoke();
	assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
	response.close();
	fail("The test is not yet implemented. Fixme!!!");

  }
  
  @Test
  public void readPhones_MultiplePhones_OrderList() throws Exception {	  
	  Mockito.when(phoneInteractors.findAllPhonesOrderByNewer())
	  	.thenReturn(Collections.<PhoneResponse>emptyList().stream());

	Response response = server.newRequest("/phones").request().buildGet().invoke();
	assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
	response.close();
	fail("The test is not yet implemented. Fixme!!!");
  }

}