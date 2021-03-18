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
@EnableJpaRepositories(entityManagerFactoryRef = "studentEntityManagerFactory"
        , basePackages = {"com.suwi.ws.multipleds.data.student.repository"}
        ,transactionManagerRef = "studentTransactionManager")
public class StudentDbConfig {@Bean(name = "studentDatasource")
@ConfigurationProperties(prefix = "spring.student.datasource")
public DataSource dataSource() {
    return DataSourceBuilder.create().build();
}


    @Bean(name="studentEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean studentEntityManagerFactory(EntityManagerFactoryBuilder builder
            , @Qualifier("studentDatasource") DataSource datasource) {

        Map<String,Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto","update");
        properties.put("hibernate.dialect","org.hibernate.dialect.Oracle10gDialect");
        return builder.dataSource(datasource)
                .properties(properties)
                .packages("com.suwi.ws.multipleds.data.student.entity")
                .persistenceUnit("Book")
                .build();
    }


    @Bean(name ="studentTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("studentEntityManagerFactory") EntityManagerFactory entityManagerFactory){
        return new JpaTransactionManager(entityManagerFactory);
    }


}
