package com.hust.manga.config.manga;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "managa")
public class MangaConfig {
    private String dbDefault;
    private String[] allowEndpoint;

    public String getEsUrl() {
        return esUrl;
    }

    public void setEsUrl(String esUrl) {
        this.esUrl = esUrl;
    }

    private String esUrl;

    public String[] getAllowEndpoint() {
        return allowEndpoint;
    }

    public void setAllowEndpoint(String[] allowEndpoint) {
        this.allowEndpoint = allowEndpoint;
    }

    public String getDbDefault() {
        return dbDefault;
    }

    public void setDbDefault(String dbDefault) {
        this.dbDefault = dbDefault;
    }
}
