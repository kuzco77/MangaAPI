package com.hust.manga.services;

import com.hust.manga.core.db.CRUDService;
import com.hust.manga.model.Manga;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MangaServices extends CRUDService<Manga, Integer> {

    @Override
    public boolean createBulk(String idSession, List<Manga> data) {
        Session session = connectorManager.openSessionById(idSession);
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            int counter = data.size();
            String originIds = data.stream().map(item -> {
                return "'" + item.getOriginId() + "'";
            }).collect(Collectors.joining(", "));
            String rawQuery = "select origin_id from manga where origin_id in (" + originIds + ")";
            SQLQuery query = session.createSQLQuery(rawQuery);
            List<Object[]> listDuplicateOriginId = query.list();
            for (int i = 0; i < counter; i++) {
                Manga item = data.get(i);
                if (listDuplicateOriginId.contains(item.getOriginId())) continue;
                session.save(item);
                if (i % 100 == 0) {
                    session.flush();
                    session.clear();
                }
            }
            tx.commit();
            session.close();
            return true;

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

}
