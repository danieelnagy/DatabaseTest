package persistence.repository;

import entities.Positions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class PositionRepository {


    private SessionFactory sessionFactory;

    public PositionRepository() {

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

    public void create(Positions position) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        position.setPosition(position.getPosition());

        session.save(position);
        session.getTransaction().commit();
        session.close();
    }

    public List<Positions> findAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Positions> positions = loadAllData(Positions.class, session);
        session.getTransaction().commit();
        session.close();
        return positions;

    }

    public Positions find(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Positions position = session.get(Positions.class, id);
        session.getTransaction().commit();
        session.close();
        return position;
    }

    public void update(Positions position) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.update(position);

        session.getTransaction().commit();
        session.close();
    }

    public void delete(Positions position) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.delete(position);

        session.getTransaction().commit();
        session.close();
    }
}
