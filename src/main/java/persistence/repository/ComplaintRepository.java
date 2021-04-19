package persistence.repository;

import entities.Complaint;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class ComplaintRepository {

    private SessionFactory sessionFactory;

    public ComplaintRepository() {

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

    public void create(Complaint complaint) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        complaint.setProduct_id(complaint.getProduct_id());
        complaint.setCustomer_id(complaint.getCustomer_id());
        complaint.setEmployee_id(complaint.getEmployee_id());
        complaint.setDate(complaint.getDate());
        complaint.setDescribe(complaint.getDescribe());

        session.save(complaint);
        System.out.println("Created");
        session.getTransaction().commit();
        session.close();
    }

    public List<Complaint> findAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Complaint> complaints = loadAllData(Complaint.class, session);
        session.getTransaction().commit();
        session.close();
        return complaints;

    }

    public Complaint find(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Complaint complaint = session.get(Complaint.class, id);
        session.getTransaction().commit();
        session.close();
        return complaint;
    }

    public void update(Complaint complaint) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.update(complaint);
        System.out.println("Updated");
        session.getTransaction().commit();
        session.close();
    }

    public void delete(Complaint complaint) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.delete(complaint);

        session.getTransaction().commit();
        session.close();
    }

}
