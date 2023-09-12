package org.danilskryl.javarush.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public abstract class Dao<T> {
    private final SessionFactory sessionFactory;
    private final Class<T> clazz;

    public Dao(SessionFactory sessionFactory, Class<T> clazz) {
        this.sessionFactory = sessionFactory;
        this.clazz = clazz;
    }

    public T getById(long id) {
        return sessionFactory.openSession().get(clazz, id);
    }

    public void remove(T t) {
        sessionFactory.openSession().remove(t);
    }

    public void removeById(long id) {
        sessionFactory.openSession().remove(id);
    }

    public T save(T t) {
        getCurrentSession().saveOrUpdate(t);
        return t;
    }

    public T update(T t) {
        getCurrentSession().merge(t);
        return t;
    }

    public List<T> getAll() {
        return sessionFactory.openSession().createQuery("FROM " + clazz.getName(), clazz).list();
    }

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
