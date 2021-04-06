package com.wish.batch.config;

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
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "h2EntityManagerFactory"
        , basePackages = {"com.wish.batch.data.h2.repository"}
        ,transactionManagerRef = "h2TransactionManager")
public class H2DbConfig {

    @Primary
    @Bean(name = "h2Datasource")
    @ConfigurationProperties(prefix = "spring.h2.datasource")
    public DataSource datasource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name="h2EntityManagerFactory")
    @PersistenceContext(unitName = "primary")
    public LocalContainerEntityManagerFactoryBean h2EntityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("h2Datasource") DataSource datasource) {

        Map<String,Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto","update");
        properties.put("hibernate.dialect","org.hibernate.dialect.H2Dialect");
        return builder.dataSource(datasource)
                .properties(properties)
                .packages("com.wish.batch.data.h2.entity")
                .build();
    }

    @Primary
    @Bean(name ="h2TransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("h2EntityManagerFactory") EntityManagerFactory entityManagerFactory){
        return new JpaTransactionManager(entityManagerFactory);
    }

}
