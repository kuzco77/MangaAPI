package com.hust.manga.core.db;


import com.hust.manga.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class Connector {
    SessionFactory sessionFactory;

    public Connector(Properties properties) {
        this.sessionFactory = initConfig(properties).buildSessionFactory();
    }

    private Configuration initConfig(Properties properties) {
        Configuration configuration = new Configuration();
        configuration.setProperties(properties);
        configuration.addAnnotatedClass(Category.class);
        configuration.addAnnotatedClass(Manga.class);
        configuration.addAnnotatedClass(Chapter.class);
        configuration.addAnnotatedClass(ChapterDetail.class);
        configuration.addAnnotatedClass(MangasCategories.class);
        return configuration;
    }
}
