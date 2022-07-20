package com.sda.lacation;

import com.sda.lacation.Location;

import java.util.List;
import java.util.Optional;

public interface LocationRepository {

//    private final SessionFactory sessionFactory;

//    public Location saveMock(Location location) {
//        location.setId(1L);
//        return location;
//    }

    Location save(Location location);

    List<Location> findAll();

    Optional<Location> findById(Long id);

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
