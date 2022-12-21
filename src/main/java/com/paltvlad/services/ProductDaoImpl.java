package com.paltvlad.services;

import com.paltvlad.data.Product;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class ProductDaoImpl implements ProductDao {

    private SessionFactoryUtils sfu;

    public ProductDaoImpl(SessionFactoryUtils sfu) {
        this.sfu = sfu;
    }

    @Override
    public Optional<Product> findById(Long id) {
        try (Session session = sfu.getSession()) {

            session.beginTransaction();
            Optional<Product> product = Optional.ofNullable(session.get(Product.class, id));
            session.getTransaction().commit();
            return product;
        }
    }

    @Override
    public Optional<Product> findByTitle(String title) {
        try (Session session = sfu.getSession()) {

            session.beginTransaction();
            Optional<Product> product = Optional.ofNullable(session
                    .createQuery("select product from Product product where product.title=:title", Product.class)
                    .setParameter("title", title)
                    .getSingleResult());
            session.getTransaction().commit();
            return product;
        }
    }

    @Override
    public List<Product> findAll() {
        try (Session session = sfu.getSession()) {

            session.beginTransaction();
            List<Product> products = session.createQuery("select p from Product p").getResultList();
            session.getTransaction().commit();
            return products;
        }
    }


    @Override
    public void saveOrUpdate(Product product) {
        try (Session session = sfu.getSession()) {

            session.beginTransaction();
            session.saveOrUpdate(product);
            session.getTransaction().commit();
        }

    }

    @Override
    public void deleteById(Long id) {
        try (Session session = sfu.getSession()) {
            if (!findById(id).isEmpty()) {
                session.beginTransaction();
                session.createQuery("delete Product p where p.id= :id")
                        .setParameter("id", id)
                        .executeUpdate();
                session.getTransaction().commit();
            }
        }
    }
}
