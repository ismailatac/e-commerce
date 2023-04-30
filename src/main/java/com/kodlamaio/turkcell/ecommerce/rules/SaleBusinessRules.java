package com.kodlamaio.turkcell.ecommerce.rules;

import com.kodlamaio.turkcell.ecommerce.common.constants.Messages;
import com.kodlamaio.turkcell.ecommerce.entities.concretes.Product;
import com.kodlamaio.turkcell.ecommerce.repository.abstracts.ProductRepository;
import com.kodlamaio.turkcell.ecommerce.repository.abstracts.SaleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SaleBusinessRules {
    private final SaleRepository repository;
    public void checkIfSaleExists(int id){
        if (!repository.existsById(id)) throw new RuntimeException(Messages.Sale.NotExists);
    }
}
