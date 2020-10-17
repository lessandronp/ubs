package br.com.lessandro.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "br.com.lessandro.repository" )
@EntityScan(basePackages = "br.com.lessandro")
@EnableTransactionManagement
public class JpaConfig {

    @Bean
    JpaTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }
}
