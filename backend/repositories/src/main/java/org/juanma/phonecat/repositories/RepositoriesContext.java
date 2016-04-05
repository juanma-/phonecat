package org.juanma.phonecat.repositories;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * Created by Juan Manuel Castillo on 15/01/16.
 */
@Configuration
//@EnableJpaAuditing
@EnableJpaRepositories("org.juanma.phonecat.repositories")
@EnableTransactionManagement
public class RepositoriesContext {

  //    @Bean
  //    public AuditorAware<UserJpa> auditorProvider() {
  //        return new AuditorBean();
  //    }

  @Bean
  LocalContainerEntityManagerFactoryBean entityManagerFactory(
      DataSource dataSource, Environment env) {

    LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
    emf.setDataSource(dataSource);
    emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
    //emf.setPersistenceXmlLocation("classpath:META-INF/persistence.xml");
    emf.setMappingResources("orm.xml");
    emf.setPackagesToScan(//"org.juanma.phonecat.model",
        "org.springframework.data.jpa.convert.threeten");

    Properties jpaProperties = new Properties();
    //Configures the used database dialect. This allows Hibernate to create SQL that is optimized
    // for the used database.
    jpaProperties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
    //If the value of this property is true, Hibernate writes all SQL statements to the console.
    jpaProperties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
    //If the value of this property is true, Hibernate will format the SQL that is written to the
    // console.
    jpaProperties.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));

    emf.setJpaProperties(jpaProperties);

    return emf;
  }

  @Bean
  JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(entityManagerFactory);
    return transactionManager;
  }
}
