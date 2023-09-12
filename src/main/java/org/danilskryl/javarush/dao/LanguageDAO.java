package org.danilskryl.javarush.dao;

import org.danilskryl.javarush.entity.Language;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class LanguageDAO extends Dao<Language> {
    public LanguageDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Language.class);
    }

    public Language getAnyLanguage() {
        Query<Language> query = getCurrentSession().createQuery("FROM Language", Language.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }
}
