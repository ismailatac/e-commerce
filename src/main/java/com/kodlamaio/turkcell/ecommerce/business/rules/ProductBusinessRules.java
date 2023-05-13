package com.kodlamaio.turkcell.ecommerce.business.rules;

import com.kodlamaio.turkcell.ecommerce.entities.concretes.Product;
import com.kodlamaio.turkcell.ecommerce.repository.abstracts.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductBusinessRules {
    private final ProductRepository repository;

    public void checkIfProductExist(int id) {
        if (!repository.existsById(id)) throw new RuntimeException("Ürün bulunamadı");
    }

    public void validateProduct(Product p) {
        checkIfPriceValid(p);
        checkIfQuantityValid(p);
        checkIfDescriptionValid(p);
    }

    private void checkIfDescriptionValid(Product p) {
        if (p.getDescription().length() < 10 || p.getDescription().length() > 50) {
            throw new IllegalArgumentException("Ürün açıklaması 10'dan küçük veya 50 karakterden büyük olmamalı.");
        }
    }

    private void checkIfQuantityValid(Product p) {
        if (p.getQuantity() < 0) {
            throw new IllegalArgumentException("Ürün miktarı sıfırdan küçük olamaz.");
        }
    }

    private void checkIfPriceValid(Product p) {
        if (p.getPrice() <= 0) {
            throw new IllegalArgumentException("Fiyat sıfırdan küçük olamaz.");
        }
    }

    public List<Product> filterProductsByisActive(boolean isActive) {
        if (isActive) {
            return repository.findAll();
        }
        return repository.findAllByisActiveIsNot(isActive);
    }
}
