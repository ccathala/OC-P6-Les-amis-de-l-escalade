package com.charles.lesamisdelescalade.consumer.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan("com.charles.lesamisdelescalade.consumer")
@PropertySource(value= {"classpath:consumerConfig.properties"})
public class ConsumerConfig {
	
	@Bean
	public String consumer() {
		return "consumer";
	}
	
	@Autowired /* Injection du bean env */
	private Environment env;

	@Bean /* Déclaration du bean à créer dataSource*/ 
	public DataSource dataSource() {
		/* Initialisation du bean dataSource à partir du fichier .properties */
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getRequiredProperty("jdbc.driverClassName"));
		dataSource.setUrl(env.getRequiredProperty("jdbc.url"));
		dataSource.setUsername(env.getRequiredProperty("jdbc.username"));
		dataSource.setPassword(env.getRequiredProperty("jdbc.password"));
		return dataSource;
	}
	

	@Bean /* Déclaration du bean jdbcTemplate */
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		/* Initialisation du bean jdbcTemplate */
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		return jdbcTemplate;

	}

}
