package com.electoscope.backend.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
@ConfigurationProperties(prefix = "huggingface.api")
public class HuggingFaceConfig {
    private String key;
    private String url;

    public void setKey(String key) {
        this.key = key;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
