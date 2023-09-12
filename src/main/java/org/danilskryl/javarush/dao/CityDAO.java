package org.danilskryl.javarush.dao;

import org.danilskryl.javarush.entity.City;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class CityDAO extends Dao<City> {
    public CityDAO(SessionFactory sessionFactory) {
        super(sessionFactory, City.class);
    }

    public City getCityByName(String name) {
        Query<City> query = getCurrentSession().createQuery("SELECT с FROM City с WHERE с.city = :NAME", City.class);
        query.setParameter("NAME", name);
        query.setMaxResults(1);
        return query.getSingleResult();
    }
}
