package com.paltvlad.services;

import com.paltvlad.data.Customer;

import org.hibernate.Session;
import org.springframework.stereotype.Service;


@Service
public class ProductService {

    private SessionFactoryUtils sfu;

    public ProductService (SessionFactoryUtils sfu) {
        this.sfu = sfu;
    }

//    private Products products;
//
//
//    public ProductService(Products products) {
//        this.products = products;
//    }
//
//    public void deleteById(Long id) {
//        products.deleteById(id);
//    }
//
//    public List<Product> getALlProducts() {
//        return products.getALlProducts();
//    }
//
//    public void changePrice(Long id, Integer percent) {
//        Product product = products.findById(id);
//
//        product.setPrice(new BigDecimal(product.getPrice() + product.getPrice() * percent / 100).setScale(2, RoundingMode.HALF_EVEN).doubleValue());
//
//    }

    public void orderListByCustomers(Long id){
        try (Session session = sfu.getSession()) {

            session.beginTransaction();
            Customer customer = session.get(Customer.class, id);
            System.out.println(customer);
            System.out.println(customer.getProducts());
            session.getTransaction().commit();

        }
    }
}
