package persistence.repository;

import entities.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class OrderRepository {

    private SessionFactory sessionFactory;

    public OrderRepository() {

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

    public void create(Order order) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        order.setCustomerId(order.getOrderId());
        order.setProductId(order.getProductId());
        order.setStock(order.getStock());
        order.setOrderDate(new java.sql.Date(new java.util.Date().getTime()));
        order.setArrivalDate(new java.sql.Date(new java.util.Date().getTime() + 5));
        order.setShipmentDate(new java.sql.Date(new java.util.Date().getTime() + 7));


        session.save(order);
        session.getTransaction().commit();
        session.close();
    }

    public List<Order> findAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Order> orders = loadAllData(Order.class, session);
        session.getTransaction().commit();
        session.close();
        return orders;

    }

    public Order find(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Order order = session.get(Order.class, id);
        session.getTransaction().commit();
        session.close();
        return order;
    }

    public void update(Order order) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.update(order);

        session.getTransaction().commit();
        session.close();
    }

    public void delete(Order order) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.delete(order);

        session.getTransaction().commit();
        session.close();
    }

}
