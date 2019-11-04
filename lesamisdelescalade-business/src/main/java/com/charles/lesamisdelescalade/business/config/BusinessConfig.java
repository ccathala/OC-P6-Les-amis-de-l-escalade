package com.charles.lesamisdelescalade.business.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import com.charles.lesamisdelescalade.consumer.config.ConsumerConfig;

@Configuration
@ComponentScan("com.charles.lesamisdelescalade.business")
@Import(ConsumerConfig.class)
public class BusinessConfig {
	
	

}