package com.electoscope.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://electoscope.vercel.app")
                .allowedOrigins("https://electoscope-asbeg2kcv-lirongzzuins-projects.vercel.app")
                .allowedMethods("*")
                .allowedHeaders("*");
    }
}
