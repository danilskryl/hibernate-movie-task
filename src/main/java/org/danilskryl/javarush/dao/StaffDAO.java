package org.danilskryl.javarush.dao;

import org.danilskryl.javarush.entity.Staff;
import org.hibernate.SessionFactory;

public class StaffDAO extends Dao<Staff> {
    public StaffDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Staff.class);
    }
}
