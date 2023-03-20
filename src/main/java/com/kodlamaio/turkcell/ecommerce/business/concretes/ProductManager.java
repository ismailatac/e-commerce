package com.kodlamaio.turkcell.ecommerce.business.concretes;

import com.kodlamaio.turkcell.ecommerce.business.abstracts.ProductService;
import com.kodlamaio.turkcell.ecommerce.entities.concretes.Product;
import com.kodlamaio.turkcell.ecommerce.repository.abstracts.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductManager implements ProductService {

    private final ProductRepository repository;

    @Autowired
    public ProductManager(ProductRepository repository) {
        this.repository = repository;
    }




    @Override
    public List<Product> getAll() {
        if (repository.getAll().size() == 0){
            throw new RuntimeException("Ürün yok;");
        }
        return repository.getAll();
    }

    @Override
    public Product add(Product p) {
        validateProduct(p);
        return repository.add(p);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public Product update(int id, Product p) {
        validateProduct(p);
        return repository.update(id,p);

    }

    @Override
    public Product getById(int id) {
        return repository.getById(id);
    }


    private  void validateProduct(Product p) {
        checkIfPriceValid(p);
        checkIfQuantityValid(p);
        checkIfDescriptionValid(p);
    }

    private  void checkIfDescriptionValid(Product p) {
        if (p.getDescription().length() < 10 || p.getDescription().length() > 50){
            throw new IllegalArgumentException("Ürün açıklaması 10'dan küçük veya 50 karakterden büyük olmamalı.");
        }
    }

    private  void checkIfQuantityValid(Product p) {
        if (p.getQuantity() < 0){
            throw new IllegalArgumentException("Ürün miktarı sıfırdan küçük olamaz.");
        }
    }

    private  void checkIfPriceValid(Product p) {
        if(p.getPrice() <= 0){
            throw new IllegalArgumentException("Fiyat sıfırdan küçük olamaz.");
        }
    }
}
