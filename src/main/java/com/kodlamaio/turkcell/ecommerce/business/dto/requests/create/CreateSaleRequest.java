package com.kodlamaio.turkcell.ecommerce.business.dto.requests.create;

import com.kodlamaio.turkcell.ecommerce.business.dto.PaymentRequest;
import com.kodlamaio.turkcell.ecommerce.entities.concretes.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateSaleRequest {
    private LocalDateTime date;
    private Product product;
    private PaymentRequest paymentRequest;
    private int quantityToBeSold;
}
