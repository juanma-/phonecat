package org.juanma.phonecat.usecases;

import org.juanma.phonecat.contracts.delivery.responsemodel.PhoneResponse;
import org.juanma.phonecat.contracts.external.PhoneRepository;
import org.juanma.phonecat.model.Phone;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * Created by Juan Manuel Castillo on 26/03/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class PhoneInteractorsTest {
  @Mock static PhoneRepository phoneRepository;
  @InjectMocks static PhoneInteractorsImpl phoneInteractors = new PhoneInteractorsImpl();


  @Test
  public void findAllPhonesByNewer_NoPhones_Empty() throws Exception {
    Mockito.when(phoneRepository.findAllByOrderByCreatedDateDesc())
        .thenReturn(Collections.<Phone>emptyList().stream());

    long phonesCount = phoneInteractors.findAllPhonesOrderByNewer().count();
    assertEquals(0, phonesCount);
  }

  @Test
  public void findAllPhonesByNewer_UniquePhone_OnlyOne() throws Exception {
    Mockito.when(phoneRepository.findAllByOrderByCreatedDateDesc())
        .thenReturn(Collections.singletonList(newPhone("unique-phone", "Unique Phone", "..."))
            .stream());

    List<PhoneResponse> phones =
        phoneInteractors.findAllPhonesOrderByNewer().collect(Collectors.toList());

    assertEquals(1, phones.size());

    PhoneResponse phone = phones.get(0);
    assertEquals("unique-phone", phone.getId());
    assertEquals("Unique Phone", phone.getName());
    assertEquals("...", phone.getSnippet());
  }
  
  @Test
  public void findAllPhonesByNewer_MultiplePhones_OrderList() throws Exception {
	  Mockito.when(phoneRepository.findAllByOrderByCreatedDateDesc())
      .thenReturn(Arrays.asList(
    		  newPhone("phone1", "phone 1", "..."), 
    		  newPhone("phone2", "phone 2", "..."),
    		  newPhone("phone3", "phone 3", "..."))
          .stream());
	  
	    List<PhoneResponse> phones =
	            phoneInteractors.findAllPhonesOrderByNewer().collect(Collectors.toList());
	    
	    assertEquals(3, phones.size());
	    assertEquals("phone1", phones.get(0).getId());
	    assertEquals("phone2", phones.get(1).getId());
	    assertEquals("phone3", phones.get(2).getId());
  }

  private static Phone newPhone(final String externalId, final String name, final String snippet) {
    return new Phone() {
      @Override
      public String getExternalId() {
        return externalId;
      }
      @Override
      public String getName() {
        return name;
      }
      @Override
      public String getSnippet() {
        return snippet;
      }
    };
  }
}