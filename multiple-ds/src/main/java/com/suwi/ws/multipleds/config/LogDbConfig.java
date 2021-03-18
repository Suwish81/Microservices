package com.suwi.ws.multipleds.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "logEntityManagerFactory"
        , basePackages = {"com.suwi.ws.multipleds.data.log.repository"}
        ,transactionManagerRef = "logTransactionManager")
public class LogDbConfig {

    @Bean(name = "logDatasource")
    @ConfigurationProperties(prefix = "spring.log.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }


    @Bean(name="logEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean bookEntityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("logDatasource") DataSource datasource) {

        Map<String,Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto","update");
        properties.put("hibernate.dialect","org.hibernate.dialect.H2Dialect");
        return builder.dataSource(datasource)
                .properties(properties)
                .packages("com.suwi.ws.multipleds.data.log.entity")
                .persistenceUnit("Log")
                .build();
    }


    @Bean(name ="logTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("logEntityManagerFactory") EntityManagerFactory entityManagerFactory){
        return new JpaTransactionManager(entityManagerFactory);
    }
}
