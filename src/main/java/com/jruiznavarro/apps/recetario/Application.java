package com.jruiznavarro.apps.recetario;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.jruiznavarro.apps.recetario.common.filter.JwtFilter;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	/**
	 * Main Spring boot method
	 * 
	 * @param args
	 *            {@link String}[]
	 */
	public static void main(final String[] args) {
		log.info("Starting application...");
		SpringApplication.run(Application.class, args); // NOSONAR
	}
	
	@Bean
	 public FilterRegistrationBean corsFilter() {
	   final CorsConfiguration config = new CorsConfiguration();
	   config.setAllowCredentials(true);
	   config.addAllowedOrigin("*");
	   config.addAllowedHeader("*");
	   config.addAllowedMethod("*");
	   final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	   source.registerCorsConfiguration("/**", config);
	   final FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
	   bean.setOrder(0);
	   return bean;
	 }
	
	@Bean
	public FilterRegistrationBean<JwtFilter> loggingFilter(){
	    FilterRegistrationBean<JwtFilter> registrationBean 
	      = new FilterRegistrationBean<>();
	         
	    registrationBean.setFilter(new JwtFilter());
	         
	    return registrationBean;    
	}

	/**
	 * Command line runner bean
	 * 
	 * @param ctx
	 *            {@link ApplicationContext}
	 * @return {@link CommandLineRunner}
	 */
	@Bean
	public CommandLineRunner commandLineRunner(final ApplicationContext ctx) {

		return args -> {

			log.debug("Let's inspect the beans provided by Spring Boot:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				log.info(beanName);
			}
		};
	}
}
