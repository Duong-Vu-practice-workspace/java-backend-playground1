package vn.edu.ptit.duongvct.playground.playground1.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfig {
    @Bean
    ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

}
