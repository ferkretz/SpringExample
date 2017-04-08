package com.kferenc.jpf.configuration;

import java.io.IOException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationPropertiesConfig {

    @Bean
    public ApplicationProperties applicationProperties() throws IOException {
        ApplicationProperties applicationPropertiesService = new ApplicationProperties();
        applicationPropertiesService.setLocation("/application.properties");
        applicationPropertiesService.loadConfig();
        return applicationPropertiesService;
    }

}
