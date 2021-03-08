package service.product;

import model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class ProductService implements IProductService {
    @PersistenceContext
    private EntityManager entityManager;
//    @Autowired
//    private EntityManager entityManager;
//    @Autowired
//    private SessionFactory sessionFactory;

    @Override
    public List<Product> showAll() {
//        Session session = sessionFactory.openSession();
//        String query = "Select p from Product as p";
//        TypedQuery<Product> queryFind = session.createQuery(query, Product.class);
//        return queryFind.getResultList();
        String query = "Select p from Product as p";
        TypedQuery<Product> queryFind = entityManager.createQuery(query, Product.class);
        return queryFind.getResultList();
    }

    @Override
    public Product findById(int id) {
//        Session session = sessionFactory.openSession();
//        String query = "select c from Product as c where c.id = :id";
//        TypedQuery<Product> queryFindId = session.createQuery(query, Product.class);
//        queryFindId.setParameter("id", id);
//        return queryFindId.getSingleResult();
        String query = "select c from Product as c where c.id = :id";
        TypedQuery<Product> queryFindId = entityManager.createQuery(query, Product.class);
        queryFindId.setParameter("id", id);
        return queryFindId.getSingleResult();
    }

    @Override
    public void update(Product model) {
//        Session session = null;
//        Transaction transaction = null;
//        try {
//            session = sessionFactory.openSession();
//            transaction = session.beginTransaction();
//            session.update(model);
//            transaction.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            if (transaction != null) {
//                transaction.rollback();
//            }
//        } finally {
//            if (session != null) {
//                session.close();
//            }
//        }
        if(model.getId() !=0){
            entityManager.merge(model);
        }
    }

    @Override
    public void save(Product model) {
//        Session session = null;
//        Transaction transaction = null;
//        try {
//            session = sessionFactory.openSession();
//            transaction = session.beginTransaction();
//            session.save(model);
//            transaction.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            if (transaction != null) {
//                transaction.rollback();
//            }
//        } finally {
//            if (session != null) {
//                session.close();
//            }
//        }
        if(model.getId() != 0){
            entityManager.merge(model);
        } else {
            entityManager.persist(model);
        }
    }

    @Override
    public void remove(int id) {
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//        try {
//            session.remove(findById(id));
//            transaction.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            if (transaction != null) {
//                transaction.rollback();
//            }
//        } finally {
//            if (session != null) ;
//            session.close();
//        }
        Product product = findById(id);
        if(product != null){
            entityManager.remove(product);
        }
    }

    @Override
    public List<Product> searchByName(String name) {
        String query = "select p from Product as p where p.name like :name";
        TypedQuery queryFind = entityManager.createQuery(query,Product.class);
        String nameFind ="%" + name + "%";
        queryFind.setParameter("name", nameFind);
        return queryFind.getResultList();
    }
}
