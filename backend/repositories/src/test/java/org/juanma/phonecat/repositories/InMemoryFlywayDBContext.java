package org.juanma.phonecat.repositories;

import org.flywaydb.core.Flyway;
import org.h2.Driver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

/**
 * Created by Juan Manuel Castillo on 16/01/16.
 */
@Configuration
public class InMemoryFlywayDBContext {

    @Bean
    DataSource dataSource() {
        DataSource dataSource = new SimpleDriverDataSource(
            new Driver(),
            "jdbc:h2:mem:dbTest;MV_STORE=FALSE;MVCC=FALSE;DB_CLOSE_DELAY=-1",
            "sa", null);

        System.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        System.setProperty("hibernate.show_sql", "true");
        System.setProperty("hibernate.format_sql", "true");

        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.setLocations("filesystem:../../db/migration");
        flyway.migrate();

        return dataSource;
    }
}
