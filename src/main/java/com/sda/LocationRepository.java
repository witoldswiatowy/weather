package com.sda;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public interface LocationRepository {

//    private final SessionFactory sessionFactory;

//    public Location saveMock(Location location) {
//        location.setId(1L);
//        return location;
//    }

    Location save(Location location);

//        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
//
////        Session session = sessionFactory.openSession();
////        try (session) {
////            Transaction transaction = session.beginTransaction();
////            session.persist(location);
////            transaction.commit();
////            return location;
////        } catch (Exception e) {
////            Transaction transaction = session.getTransaction();
////            transaction.rollback();
////            throw new RuntimeException("Operacja na bazie danych nie powiodła się");
////        }
//        sessionFactory = HibernateUtils.getSessionFactory();
//
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//
//        session.persist(location);
//
//        transaction.commit();
//        session.close();
//
//        return location;
        //todo spojrzec jeszcze raz ktore lepsze/łatwiejsze/czytelniejsze i poprawniejsze...
//    }
}
