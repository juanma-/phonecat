package org.juanma.phonecat.usecase;

import org.juanma.phonecat.contracts.delivery.PhoneInteractors;
import org.juanma.phonecat.contracts.external.PhoneRepository;
import org.juanma.phonecat.model.Phone;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

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
  public void findAllPhones_NoPhones_Empty() throws Exception {
    Mockito.when(phoneRepository.findAllByOrderByCreatedDateDesc())
        .thenReturn(Collections.<Phone>emptyList().stream());

    long phonesCount = phoneInteractors.findAllPhones().count();
    assertEquals(0, phonesCount);
  }

  @Test
  public void findAllPhones_UniquePhone_OnlyOne() throws Exception {
    Mockito.when(phoneRepository.findAllByOrderByCreatedDateDesc())
        .thenReturn(Collections.singletonList(newPhone("unique-phone", "Unique Phone", "..."))
            .stream());

    List<PhoneInteractors.PhoneVO> phones =
        phoneInteractors.findAllPhones().collect(Collectors.toList());

    assertEquals(1, phones.size());

    PhoneInteractors.PhoneVO phone = phones.get(0);
    assertEquals(1, phone.getAge());
    assertEquals("unique-phone", phone.getId());
    assertEquals("Unique Phone", phone.getName());
    assertEquals("...", phone.getSnippet());
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