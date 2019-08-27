package com.charles.lesamisdelescalade.webapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.charles.lesamisdelescalade.business.config.BusinessConfig;

@Configuration
@ComponentScan("com.charles.lesamisdelescalade.webapp")
@Import(BusinessConfig.class)
public class WebappConfig {
	

}
