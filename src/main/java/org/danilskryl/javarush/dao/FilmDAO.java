package org.danilskryl.javarush.dao;

import org.danilskryl.javarush.entity.Film;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class FilmDAO extends Dao<Film> {
    public FilmDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Film.class);
    }

    public Film getAvailableFilm() {
        Query<Film> query = getCurrentSession()
                .createQuery("FROM Film f WHERE f.id NOT IN (SELECT film.id FROM Inventory)", Film.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }
}
