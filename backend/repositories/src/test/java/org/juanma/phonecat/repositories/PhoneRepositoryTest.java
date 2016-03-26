package org.juanma.phonecat.repositories;


import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.juanma.phonecat.model.Phone;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Juan Manuel Castillo on 21/03/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DbUnitInMemoryFlywayDbContext.class, RepositoriesContext.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class,
    DbUnitTestExecutionListener.class})
public class PhoneRepositoryTest {
  @Inject PhoneRepository phoneRepository;

  @Test
  public void findAllByOrderByCreatedDateDesc_NoPhones_Empty() throws Exception {
    List<Phone> phones = phoneRepository.findAllByOrderByCreatedDateDesc()
        .collect(Collectors.toList());

    Assert.assertTrue(phones.isEmpty());
  }

  @Test
  @Transactional
  @DatabaseSetup("classpath:uniquePhone.xml")
  public void findAllByOrderByCreatedDateDesc_UniquePhone_OnlyOne() throws Exception {
    List<Phone> phones = phoneRepository.findAllByOrderByCreatedDateDesc()
        .collect(Collectors.toList());

    Assert.assertEquals(1, phones.size());
  }

  @Test
  @Transactional
  @DatabaseSetup("classpath:somePhones.xml")
  public void findAllByOrderByCreatedDateDesc_SomePhones_NewerFirst() throws Exception {
    List<Phone> phones = phoneRepository.findAllByOrderByCreatedDateDesc()
        .collect(Collectors.toList());

    Assert.assertEquals(3, phones.size());
    //Newest
    Assert.assertEquals("phone-2", phones.get(0).getExternalId());
    //Oldest
    Assert.assertEquals("phone-3", phones.get(2).getExternalId());
  }
}