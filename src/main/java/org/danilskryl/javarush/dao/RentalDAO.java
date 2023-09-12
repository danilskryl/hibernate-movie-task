package org.danilskryl.javarush.dao;

import org.danilskryl.javarush.entity.Rental;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class RentalDAO extends Dao<Rental> {
    public RentalDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Rental.class);
    }
    public Rental getUnreternedRental() {
        Query<Rental> query = getCurrentSession().createQuery("FROM Rental r WHERE r.returnDate IS NULL", Rental.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }
}
