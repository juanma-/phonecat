package org.juanma.phonecat.repositories.testutils;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

/**
 * Created by Juan Manuel Castillo on 16/01/16.
 */
@Configuration
@PropertySource("classpath:repositories-test.properties")
public class InMemoryFlywayDBContext {
	@Autowired Environment env;

    @Bean
    DataSource dataSource() {  
        DataSource dataSource = new SimpleDriverDataSource(
            new org.h2.Driver(),
            "jdbc:h2:mem:dbTest;MV_STORE=FALSE;MVCC=FALSE;DB_CLOSE_DELAY=-1",
            "sa", null);
        
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.setLocations(env.getRequiredProperty("flyway.locations"));
        flyway.migrate();
        
        return dataSource;
    }
}
