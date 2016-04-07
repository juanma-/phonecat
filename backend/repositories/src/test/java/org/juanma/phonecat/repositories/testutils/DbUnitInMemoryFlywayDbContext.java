package org.juanma.phonecat.repositories.testutils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.springtestdbunit.bean.DatabaseConfigBean;
import com.github.springtestdbunit.bean.DatabaseDataSourceConnectionFactoryBean;

/**
 * Created by Juan Manuel Castillo on 17/01/16.
 */
@Configuration
public class DbUnitInMemoryFlywayDbContext extends InMemoryFlywayDBContext {
    @Bean
    public DatabaseConfigBean dbUnitDatabaseConfig() {
        DatabaseConfigBean dbConfig = new DatabaseConfigBean();
        dbConfig.setDatatypeFactory(new org.dbunit.ext.h2.H2DataTypeFactory());
        return dbConfig;
    }

    @Bean
    public DatabaseDataSourceConnectionFactoryBean dbUnitDatabaseConnection() {
        DatabaseDataSourceConnectionFactoryBean dbConnection = new DatabaseDataSourceConnectionFactoryBean(dataSource());
        dbConnection.setDatabaseConfig(dbUnitDatabaseConfig());
        return dbConnection;
    }
}
