package com.suwi.ws.multipleds.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory"
        , basePackages = {"com.suwi.ws.multipleds.data.user.repository"}
        ,transactionManagerRef = "transactionManager")
public class UserDbConfig {

    @Primary
    @Bean(name = "datasource")
    @ConfigurationProperties(prefix = "spring.user.datasource")
    public DataSource datasource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name="entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("datasource") DataSource datasource) {

        Map<String,Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto","update");
        properties.put("hibernate.dialect","org.hibernate.dialect.MySQL5Dialect");
        return builder.dataSource(datasource)
                .properties(properties)
                .packages("com.suwi.ws.multipleds.data.user.entity")
                .persistenceUnit("User")
                .build();
    }

    @Primary
    @Bean(name ="transactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory){
        return new JpaTransactionManager(entityManagerFactory);
    }
}
