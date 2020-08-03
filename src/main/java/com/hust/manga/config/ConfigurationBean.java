package com.hust.manga.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.hust.manga.config.manga.MangaConfig;
import com.hust.manga.core.AffiliateRestTemplate;
import com.hust.manga.core.db.Connector;
import com.hust.manga.core.db.ConnectorManager;
import com.hust.manga.core.db.DialectSQL;
import com.hust.manga.core.db.DataBaseProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Properties;

@Configuration
public class  ConfigurationBean {

    @Autowired
    MangaConfig mangaConfig;
    @Bean
    public ConnectorManager createConnectors() {
        String database = mangaConfig.getDbDefault();
        ConnectorManager connectorManager = new ConnectorManager();
        Properties properties = DataBaseProperties.createProperties(DialectSQL.MYSQL, database);
        Connector connector = new Connector(properties);
        connectorManager.addConnector(database, connector);
        System.out.println("Connected to database " + database);
        return connectorManager;
    }

    @Bean
    public InternalResourceViewResolver defaultViewResolver() {
        return new InternalResourceViewResolver();
    }

    @Bean
    public AffiliateRestTemplate createAffiliateRestTemplate(){
        return new AffiliateRestTemplate();
    }

    @Bean
    public ObjectMapper createObjectMapper(){
        return new ObjectMapper();
    }
}

