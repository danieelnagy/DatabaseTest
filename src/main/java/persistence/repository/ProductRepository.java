package persistence.repository;

import entities.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class ProductRepository {

    private SessionFactory sessionFactory;

    public ProductRepository() {

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

    public void create(Product product) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        product.setName(product.getName());
        product.setPrice(product.getPrice());
        product.setStock(product.getStock());
        product.setWareHouseId(product.getWareHouseId());
        product.setPositionId(product.getPositionId());
        session.save(product);
        session.getTransaction().commit();
        session.close();
    }

    public List<Product> findAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Product> products = loadAllData(Product.class, session);
        session.getTransaction().commit();
        session.close();
        return products;

    }

    public Product find(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Product product = session.get(Product.class, id);
        session.getTransaction().commit();
        session.close();
        return product;
    }

    public void update(Product product) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.update(product);

        session.getTransaction().commit();
        session.close();
    }

    public void delete(Product product) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.delete(product);

        session.getTransaction().commit();
        session.close();
    }

}
