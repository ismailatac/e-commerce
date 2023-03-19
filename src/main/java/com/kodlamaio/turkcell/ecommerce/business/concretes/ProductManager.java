package com.kodlamaio.turkcell.ecommerce.business.concretes;

import com.kodlamaio.turkcell.ecommerce.business.abstracts.ProductService;
import com.kodlamaio.turkcell.ecommerce.entities.concretes.Product;
import com.kodlamaio.turkcell.ecommerce.repository.abstracts.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductManager implements ProductService {

    ProductRepository repository;

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
    public void add(Product p) {
        repository.add(p);
    }

    @Override
    public boolean delete(int id) {
        repository.delete(id);
        return true;
    }

    @Override
    public boolean update(Product p) {
        repository.update(p);
        return true;
    }

    @Override
    public Product getById(int id) {
        return repository.getById(id);
    }

}
