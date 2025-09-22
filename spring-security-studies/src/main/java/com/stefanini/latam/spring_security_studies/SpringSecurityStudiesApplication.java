package com.stefanini.latam.spring_security_studies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringSecurityStudiesApplication {


	public static void main(String[] args) {

        SpringApplication.run(SpringSecurityStudiesApplication.class, args);

        System.out.println(new BCryptPasswordEncoder().encode("123456"));


	}

}
