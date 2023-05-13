package com.kodlamaio.turkcell.ecommerce.business.rules;

import com.kodlamaio.turkcell.ecommerce.common.constants.Messages;
import com.kodlamaio.turkcell.ecommerce.core.exceptions.BusinessException;
import com.kodlamaio.turkcell.ecommerce.entities.concretes.Product;
import com.kodlamaio.turkcell.ecommerce.repository.abstracts.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
            throw new BusinessException(Messages.Product.DescriptionLength);
        }
    }

    private void checkIfQuantityValid(Product p) {
        if (p.getQuantity() < 0) {
            throw new BusinessException(Messages.Product.LessThanZero);
        }
    }

    private void checkIfPriceValid(Product p) {
        if (p.getPrice() <= 0) {
            throw new BusinessException(Messages.Product.LessThanZero);
        }
    }


}
