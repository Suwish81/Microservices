package com.wish.batch.config;

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
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "oracleEntityManagerFactory"
        , basePackages = {"com.wish.batch.data.oracle.repository"}
        ,transactionManagerRef = "oracleTransactionManager")
public class OracleDbConfig {

    @Bean(name = "oracleDatasource")
    @ConfigurationProperties(prefix = "spring.oracle.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name="oracleEntityManagerFactory")
    @PersistenceContext(unitName = "oracle")
    public LocalContainerEntityManagerFactoryBean bookEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                           @Qualifier("oracleDatasource") DataSource datasource) {

        Map<String,Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto","update");
        properties.put("hibernate.dialect","org.hibernate.dialect.Oracle10gDialect");
        return builder.dataSource(datasource)
                .properties(properties)
                .packages("com.wish.batch.data.oracle.entity")
                .build();
    }
    @Bean(name ="oracleTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("oracleEntityManagerFactory") EntityManagerFactory entityManagerFactory){
        return new JpaTransactionManager(entityManagerFactory);
    }

    /*
        @PersistenceContext(unitName = "secondary")
        private EntityManager entityManager;
     */

}
