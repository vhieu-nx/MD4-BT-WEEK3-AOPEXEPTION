package service.product;

import model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;


public class ProductService implements IProductService {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Product> showAll() {
        Session session = sessionFactory.openSession();
        String query = "Select p from Product as p";
        TypedQuery<Product> queryFind = session.createQuery(query, Product.class);
        return queryFind.getResultList();
    }

    @Override
    public Product findById(int id) {
        Session session = sessionFactory.openSession();
        String query = "select c from Product as c where c.id = :id";
        TypedQuery<Product> queryFindId = session.createQuery(query, Product.class);
        queryFindId.setParameter("id", id);
        return queryFindId.getSingleResult();
    }

    @Override
    public void update(Product model) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(model);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void save(Product model) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(model);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void remove(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.remove(findById(id));
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) ;
            session.close();
        }
    }

    @Override
    public Product searchByName(String name) {
        return null;
    }
}
