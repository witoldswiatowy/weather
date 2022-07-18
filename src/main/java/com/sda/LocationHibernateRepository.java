package com.sda;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

@RequiredArgsConstructor
public class LocationHibernateRepository implements LocationRepository {

    private final SessionFactory sessionFactory;

    @Override
    public Location save(Location location) {
        Session session = sessionFactory.openSession();
        try (session) {
            Transaction transaction = session.beginTransaction();
            session.persist(location);
            transaction.commit();
            return location;
        } catch (Exception e) {
            Transaction transaction = session.getTransaction();
            transaction.rollback();
            throw new RuntimeException("Operacja na bazie danych nie powiodła się");
        }
    }
}
