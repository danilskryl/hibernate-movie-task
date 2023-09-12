package org.danilskryl.javarush.dao;

import org.danilskryl.javarush.entity.Store;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class StoreDAO extends Dao<Store> {
    public StoreDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Store.class);
    }

    public Store getAnyStore() {
        Query<Store> query = getCurrentSession().createQuery("FROM Store", Store.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }
}
