package com.stackroute.activitystream.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.web.client.RestTemplate;

import com.stackroute.activitystream.backend.model.UserRegistration;



@SpringBootApplication(scanBasePackages="com.stackroute.activitystream")
@EntityScan(basePackageClasses=UserRegistration.class)
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}
	
	
	@Bean(value = "sessionFactory")
	public HibernateJpaSessionFactoryBean sessionFactory() {
	    return new HibernateJpaSessionFactoryBean();
	}
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
}
