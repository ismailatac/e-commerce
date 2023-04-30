package com.kodlamaio.turkcell.ecommerce.business.dto.requests.create;

import com.kodlamaio.turkcell.ecommerce.business.dto.requests.PaymentRequest;
import com.kodlamaio.turkcell.ecommerce.entities.concretes.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Dictionary;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateSaleRequest {
    private LocalDateTime date;
    private List<Product> products;
    private PaymentRequest paymentRequest;
}
