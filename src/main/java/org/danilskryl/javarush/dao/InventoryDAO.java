package org.danilskryl.javarush.dao;

import org.danilskryl.javarush.entity.Inventory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class InventoryDAO extends Dao<Inventory> {
    public InventoryDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Inventory.class);
    }

    public Inventory getFreeInventory() {
        try (Session session = getCurrentSession()) {
            Query<Inventory> query = session.createQuery
                    ("SELECT Inventory FROM Inventory i " +
                            "WHERE Rental.inventory.id <> i.id OR Rental.returnDate IS NOT NULL", Inventory.class);
            query.setMaxResults(1);
            return query.getSingleResult();
        }
    }
}
