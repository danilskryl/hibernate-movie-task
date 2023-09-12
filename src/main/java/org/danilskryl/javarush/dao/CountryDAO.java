package org.danilskryl.javarush.dao;

import org.danilskryl.javarush.entity.Country;
import org.hibernate.SessionFactory;

public class CountryDAO extends Dao<Country> {
    public CountryDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Country.class);
    }
}
