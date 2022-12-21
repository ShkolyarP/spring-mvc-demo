package com.paltvlad.services;

import com.paltvlad.data.Customer;
import com.paltvlad.data.Product;

import java.util.List;
import java.util.Optional;

public interface CustomerDao {
    Optional<Customer> findById(Long id);
    Optional<Customer> findByName(String name);
    List<Customer> findAll();

    void saveOrUpdate(Customer customer);
    void deleteById(Long id);
}
