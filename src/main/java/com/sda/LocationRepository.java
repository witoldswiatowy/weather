package com.sda;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

@AllArgsConstructor
public class LocationRepository {

    private SessionFactory sessionFactory;

    public Location saveMock(Location location) {
        location.setId(1L);
        return location;
    }

    public Location save(Location location){

//        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        sessionFactory = HibernateUtils.getSessionFactory();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.persist(location);

        transaction.commit();
        session.close();

        return location;

//        Session session = sessionFactory.openSession();
//        try (session) {
//            Transaction transaction = session.beginTransaction();
//            session.persist(location);
//            transaction.commit();
//            return location;
//        } catch (Exception e) {
//            Transaction transaction = session.getTransaction();
//            transaction.rollback();
//            throw new RuntimeException("Operacja na bazie danych nie powiodła się");
//        }
        //todo spojrzec jeszcze raz ktore lepsze/łatwiejsze/czytelniejsze i poprawniejsze...
    }
}
