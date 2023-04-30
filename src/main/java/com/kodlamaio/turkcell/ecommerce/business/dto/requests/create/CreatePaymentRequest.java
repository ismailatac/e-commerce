package com.kodlamaio.turkcell.ecommerce.business.dto.requests.create;

import com.kodlamaio.turkcell.ecommerce.business.dto.PaymentRequest;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreatePaymentRequest extends PaymentRequest {
    @Min(value = 1)
    private double balance;
}
