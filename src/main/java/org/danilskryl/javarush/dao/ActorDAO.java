package org.danilskryl.javarush.dao;

import org.danilskryl.javarush.entity.Actor;
import org.hibernate.SessionFactory;

public class ActorDAO extends Dao<Actor> {
    public ActorDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Actor.class);
    }
}
