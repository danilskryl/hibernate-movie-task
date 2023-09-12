package org.danilskryl.javarush.dao;

import org.danilskryl.javarush.entity.Category;
import org.hibernate.SessionFactory;

public class CategoryDAO extends Dao<Category> {
    public CategoryDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Category.class);
    }
}
