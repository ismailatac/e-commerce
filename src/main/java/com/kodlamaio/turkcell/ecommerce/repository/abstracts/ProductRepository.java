package com.kodlamaio.turkcell.ecommerce.repository.abstracts;


import com.kodlamaio.turkcell.ecommerce.entities.concretes.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

//    List<Product> getAll();
//    Product add(Product p);
//    void delete(int id);
//    Product update(int id,Product p);
//    Product getById(int id);
}
