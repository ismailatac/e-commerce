package com.kodlamaio.turkcell.ecommerce.business.dto.requests.create;

import com.kodlamaio.turkcell.ecommerce.business.dto.PaymentRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateSaleRequest {
    private int productId;
    private PaymentRequest paymentRequest;
    private int quantityToBeSold;
}
