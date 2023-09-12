package org.danilskryl.javarush.dao;

import org.danilskryl.javarush.entity.Payment;
import org.hibernate.SessionFactory;

public class PaymentDAO extends Dao<Payment> {
    public PaymentDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Payment.class);
    }
}
