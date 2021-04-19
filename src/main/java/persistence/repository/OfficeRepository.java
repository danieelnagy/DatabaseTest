package persistence.repository;

import entities.Office;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class OfficeRepository {

    private SessionFactory sessionFactory;

    public OfficeRepository() {

        setup();
    }

    public void setup() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .loadProperties("hibernate.cfg.xml")
                .build();
        sessionFactory = new Configuration().buildSessionFactory(registry);
    }

    public void shutdown() {

        sessionFactory.close();
    }

    private static <T> List<T> loadAllData(Class<T> type, Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        criteria.from(type);
        List<T> data = session.createQuery(criteria).getResultList();
        return data;
    }

    public void create(Office office) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        office.setName(office.getName());
        office.setAdress(office.getAdress());
        session.save(office);
        session.getTransaction().commit();
        session.close();
    }

    public List<Office> findAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Office> offices = loadAllData(Office.class, session);
        session.getTransaction().commit();
        session.close();
        return offices;

    }

    public Office find(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Office office = session.get(Office.class, id);
        session.getTransaction().commit();
        session.close();
        return office;
    }

    public void update(Office office) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.update(office);

        session.getTransaction().commit();
        session.close();
    }

    public void delete(Office office) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.delete(office);

        session.getTransaction().commit();
        session.close();
    }

}
