package com.charles.lesamisdelescalade.consumer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.charles.lesamisdelescalade.consumer")
public class ConsumerConfig {
	
	@Bean
	public String consumer() {
		return "consumer";
	}

}
