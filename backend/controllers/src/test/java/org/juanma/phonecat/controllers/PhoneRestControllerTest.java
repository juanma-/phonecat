package org.juanma.phonecat.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Collections;
import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.juanma.phonecat.contracts.delivery.PhoneInteractors;
import org.juanma.phonecat.contracts.delivery.responsemodel.PhoneResponse;
import org.juanma.phonecat.controllers.PhoneRestController.PhoneRestAdapter;
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
  @Mock 
  private static PhoneInteractors phoneInteractors;
  @InjectMocks 
  private static PhoneRestController phoneRestController = new PhoneRestController();
  
  private static InMemoryRestServer server;

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
    
    List<PhoneRestAdapter> phones = response.readEntity(new GenericType<List<PhoneRestAdapter>>() {});
    assertEquals(true, phones.isEmpty());

    response.close();
  }

  @Test
  public void readPhones_UniquePhone_OnlyOne() throws Exception {
	  
	Mockito.when(phoneInteractors.findAllPhonesOrderByNewer())
	  	.thenReturn(Collections.singletonList(newPhoneResponse("phone", "Phone", "Phone ...", "phone.0.jpg")).stream());

	Response response = server.newRequest("/phones").request().buildGet().invoke();
	assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
	
	List<PhoneRestAdapter> phones = response.readEntity(new GenericType<List<PhoneRestAdapter>>() {});
	assertEquals(false, phones.isEmpty());
	assertEquals(1, phones.size());
	
	PhoneRestAdapter phone = phones.get(0);
	assertEquals(0, phone.getAge());
	assertEquals("phone", phone.getId());
	assertEquals("Phone", phone.getName());
	assertEquals("img/phones/phone.0.jpg", phone.getImageUrl());
	assertEquals("Phone ...", phone.getSnippet());
	
	response.close();
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
  
  private static PhoneResponse newPhoneResponse(final String id, final String name, final String snippet, final String imageId) {
	  return new PhoneResponse() {		
			@Override
			public String getId() {
				return id;
			}
			@Override
			public String getName() {
				return name;
			}	
			@Override
			public String getSnippet() {
				return snippet;
			}						
			@Override
			public String getImageId() {
				return null;
			}		
	};
  }
}