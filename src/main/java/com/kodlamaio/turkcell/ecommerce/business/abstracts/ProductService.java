package com.kodlamaio.turkcell.ecommerce.business.abstracts;

import com.kodlamaio.turkcell.ecommerce.entities.concretes.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAll();
    Product add(Product p);
    void delete(int id);
    Product update(int id, Product p);
    Product getById(int id);
}
