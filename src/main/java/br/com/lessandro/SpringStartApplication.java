package br.com.lessandro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * 
 * @author Lessandro
 *
 */
@SpringBootApplication(scanBasePackages = {"br.com.lessandro"})
@EnableJpaAuditing
public class SpringStartApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringStartApplication.class, args);
	}

}
