package com.kodlamaio.turkcell.ecommerce.repository.concretes;

import com.kodlamaio.turkcell.ecommerce.entities.concretes.Product;
import com.kodlamaio.turkcell.ecommerce.repository.abstracts.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryProductRepository implements ProductRepository {

    List<Product> products;

    public InMemoryProductRepository() {
        products = new ArrayList<>();
        products.add(new Product(1,"Google Pixel 7 Pro",5,7000,"Android 13"));
        products.add(new Product(2,"Google Pixel 6A",20,5000,"Android 12"));
        products.add(new Product(3,"Google Pixel 6 Pro",25,4500,"Android 12"));
        products.add(new Product(4,"Google Pixel 5A",50,4450,"Android 11"));



    }

    @Override
    public List<Product> getAll() {
        return products;
    }

    @Override
    public Product add(Product p) {
        products.add(p);
        return p;
    }

    @Override
    public void delete(int id) {
        Product p = this.getById(id);
        products.remove(p);
    }

    @Override
    public Product update(int id, Product p) {

        Product updateProduct = this.getById(p.getId());
        if(updateProduct != null) {
            updateProduct.setQuantity(p.getQuantity());
            updateProduct.setName(p.getName());
            updateProduct.setDescription(p.getDescription());
            updateProduct.setPrice(p.getPrice());
            updateProduct.setId(p.getId());
            this.delete(p.getId());
            products.add(updateProduct);
            return updateProduct;
        }
        return null;

    }

    @Override
    public Product getById(int id) {
        for (Product p4 : products){
            if (id == p4.getId()) {
                return p4;
            }
            }
        return null;
        }
    }

