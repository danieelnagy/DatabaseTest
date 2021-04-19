package persistence.repository;

import entities.WareHouse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class WarehouseRepository {

    private SessionFactory sessionFactory;

    public WarehouseRepository() {

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

    public void create(WareHouse wareHouse) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        wareHouse.setName(wareHouse.getName());
        wareHouse.setAdress(wareHouse.getAdress());


        session.save(wareHouse);
        session.getTransaction().commit();
        session.close();
    }

    public List<WareHouse> findAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<WareHouse> wareHouses = loadAllData(WareHouse.class, session);
        session.getTransaction().commit();
        session.close();
        return wareHouses;

    }

    public WareHouse find(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        WareHouse wareHouse = session.get(WareHouse.class, id);
        session.getTransaction().commit();
        session.close();
        return wareHouse;
    }

    public void update(WareHouse wareHouse) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.update(wareHouse);

        session.getTransaction().commit();
        session.close();
    }

    public void delete(WareHouse wareHouse) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.delete(wareHouse);

        session.getTransaction().commit();
        session.close();
    }
}
