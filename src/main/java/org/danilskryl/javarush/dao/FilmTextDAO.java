package org.danilskryl.javarush.dao;

import org.danilskryl.javarush.entity.FilmText;
import org.hibernate.SessionFactory;

public class FilmTextDAO extends Dao<FilmText> {
    public FilmTextDAO(SessionFactory sessionFactory) {
        super(sessionFactory, FilmText.class);
    }
}
