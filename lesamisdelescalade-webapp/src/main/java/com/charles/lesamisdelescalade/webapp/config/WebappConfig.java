package com.charles.lesamisdelescalade.webapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import com.charles.lesamisdelescalade.business.config.BusinessConfig;
import com.charles.lesamisdelescalade.model.beans.Utilisateur;

@Configuration
@ComponentScan("com.charles.lesamisdelescalade.webapp")
@Import(BusinessConfig.class)
public class WebappConfig {
	
//	@Bean
//	public Utilisateur sessionUtilisateur() {
//		return new Utilisateur();
//	}
	

}
