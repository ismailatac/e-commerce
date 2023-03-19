package com.kodlamaio.turkcell.ecommerce.repository.abstracts;


import com.kodlamaio.turkcell.ecommerce.entities.concretes.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> getAll();
    void add(Product p);
    boolean delete(int id);
    boolean update(Product p);
    Product getById(int id);
}
