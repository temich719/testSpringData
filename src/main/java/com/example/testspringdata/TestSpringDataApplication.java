package com.example.testspringdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
//@EnableWebMvc
public class TestSpringDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestSpringDataApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                        .addMapping("/**")
                        .allowedMethods("*")
                        .allowedOrigins("*");
                        //.allowedHeaders("X-Requested-With, Content-Type, Access-Control-Allow-Headers, Access-Control-Allow-Origin, Authorization")
                        //.allowCredentials(true);
            }
        };
    }

}
