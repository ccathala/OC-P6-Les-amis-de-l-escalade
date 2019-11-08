package com.charles.lesamisdelescalade.consumer.config;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("com.charles.lesamisdelescalade.consumer")
@PropertySource(value = { "classpath:consumerConfig.properties" })
@EnableTransactionManagement
public class ConsumerConfig {
	

	//===================================================================================
	
//	/* Uncomment to use DataSource data from consumerConfig.properties file */
//	
//	@Autowired /* Injection du bean env */
//	private Environment env;
//
//	/**
//	 * Initialization of dataSource bean with data from consumerConfig.properties file
//	 * 
//	 * @return
//	 */
//	@Bean  
//	public DataSource dataSource() {
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName(env.getRequiredProperty("jdbc.driverClassName"));
//		dataSource.setUrl(env.getRequiredProperty("jdbc.url"));
//		dataSource.setUsername(env.getRequiredProperty("jdbc.username"));
//		dataSource.setPassword(env.getRequiredProperty("jdbc.password"));
//		return dataSource;
//	}
	
	//===================================================================================

	/* Uncomment to use DataSource data from Tomcat context.xml file */
	
	/**
	 * Initialization of bean dataSource with data from Tomcat context.xml file
	 * 
	 * @return
	 */
	@Bean
	public DataSource dataSource() {
		
		Context initCtx;
		DataSource ds = null;
		try {
			// Obtain our environment naming context
			initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			// Look up our data source
			ds = (DataSource) envCtx.lookup("jdbc/lesAmisDeLEscalade");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return ds;
	}

	//===================================================================================
	
	/**
	 * Initialization of jdbcTemplate bean
	 * 
	 * @return
	 */
	@Bean /* Déclaration du bean jdbcTemplate */
	public JdbcTemplate jdbcTemplate() {
		/* Initialisation du bean jdbcTemplate */
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		return jdbcTemplate;

	}

	/**
	 * Initialization of  txManager bean
	 * 
	 * @return
	 */
	@Bean
	public PlatformTransactionManager txManager() {
		return new DataSourceTransactionManager(dataSource());
	}

}
