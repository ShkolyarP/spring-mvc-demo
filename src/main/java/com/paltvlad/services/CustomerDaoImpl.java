package com.paltvlad.services;

import com.paltvlad.data.Customer;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class CustomerDaoImpl implements CustomerDao {

    private SessionFactoryUtils sfu;

    public CustomerDaoImpl(SessionFactoryUtils sfu) {
        this.sfu = sfu;
    }

    @Override
    public Optional<Customer> findById(Long id) {
        try (Session session = sfu.getSession()) {

            session.beginTransaction();
            Optional<Customer> customer = Optional.ofNullable(session.get(Customer.class, id));
            session.getTransaction().commit();
            return customer;
        }
    }

    @Override
    public Optional<Customer> findByName(String name) {
        try (Session session = sfu.getSession()) {

            session.beginTransaction();
            Optional<Customer> customer = Optional.ofNullable(session
                    .createQuery("select customer from Customer customer where customer.name=:name", Customer.class)
                    .setParameter("name", name)
                    .getSingleResult());
            session.getTransaction().commit();
            return customer;
        }
    }

    @Override
    public List<Customer> findAll() {
        try (Session session = sfu.getSession()) {

            session.beginTransaction();
            List<Customer> customers = session.createQuery("select c from Customer c").getResultList();
            session.getTransaction().commit();
            return customers;
        }
    }

    @Override
    public void saveOrUpdate(Customer customer) {
        try (Session session = sfu.getSession()) {

            session.beginTransaction();
            session.saveOrUpdate(customer);
            session.getTransaction().commit();
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = sfu.getSession()) {
            if (!findById(id).isEmpty()) {
                session.beginTransaction();
                session.createQuery("delete Customer c where c.id= :id")
                        .setParameter("id", id)
                        .executeUpdate();
                session.getTransaction().commit();
            }
        }
    }
}
