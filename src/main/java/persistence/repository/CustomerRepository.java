package persistence.repository;

import entities.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class CustomerRepository {

    private SessionFactory sessionFactory;

    public CustomerRepository() {
        setup();
    }

    public void setup() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure()
                .loadProperties("hibernate.cfg.xml").build();
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

    public void create(Customer c) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        c.setName(c.getName());
        c.setAdress(c.getAdress());
        session.save(c);
        session.getTransaction().commit();
        session.close();
    }

    public List<Customer> findAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Customer> customers = loadAllData(Customer.class, session);
        session.getTransaction().commit();
        session.close();
        return customers;

    }

    public Customer find(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Customer customer = session.get(Customer.class, id);
        session.getTransaction().commit();
        session.close();
        return customer;
    }

    public void update(Customer customer) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.update(customer);

        session.getTransaction().commit();
        session.close();
    }

    public void delete(Customer customer) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.delete(customer);

        session.getTransaction().commit();
        session.close();
    }

}
