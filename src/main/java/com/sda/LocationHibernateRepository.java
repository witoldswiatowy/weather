package com.sda;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

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

    @Override
    public List<Location> findAll() {
        Session session = sessionFactory.openSession();
        try (session) {
            Transaction transaction = session.beginTransaction();
            Query<Location> locationQuery = session.createQuery("SELECT DISTINCT l FROM Location l", Location.class);
            List<Location> resultLocations = locationQuery.getResultList();
            transaction.commit();
            resultLocations.forEach(System.out::println);
            return resultLocations;
        } catch (Exception e) {
            Transaction transaction = session.getTransaction();
            transaction.rollback();
            throw new RuntimeException("Operacja na bazie danych nie powiodła się");
        }
    }

    @Override
    public Optional<Location> findById(Long id) {
        Session session = sessionFactory.openSession();
        try (session) {
            Transaction transaction = session.beginTransaction();
            Location location = session.get(Location.class, id);
            transaction.commit();
            return Optional.ofNullable(location);
        } catch (Exception e) {
            Transaction transaction = session.getTransaction();
            transaction.rollback();
            System.out.println("Nie ma lokalizacji o podanym id");
            return Optional.empty();
        }
    }
//        public Location saveMock(Location location) {  //TODO mock na mainie
//        location.setId(1L);
//        return location;
//    }
}
