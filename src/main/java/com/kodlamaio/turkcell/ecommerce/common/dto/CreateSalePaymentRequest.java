package com.kodlamaio.turkcell.ecommerce.common.dto;

import com.kodlamaio.turkcell.ecommerce.business.dto.requests.PaymentRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateSalePaymentRequest extends PaymentRequest {
    private double price;
}
