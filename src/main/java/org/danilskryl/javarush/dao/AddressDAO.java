package org.danilskryl.javarush.dao;

import org.danilskryl.javarush.entity.Address;
import org.hibernate.SessionFactory;

public class AddressDAO extends Dao<Address> {
    public AddressDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Address.class);
    }
}
