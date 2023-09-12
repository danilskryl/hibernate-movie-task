package org.danilskryl.javarush.dao;

import org.danilskryl.javarush.entity.Customer;
import org.hibernate.SessionFactory;

public class CustomerDAO extends Dao<Customer> {
    public CustomerDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Customer.class);
    }
}
