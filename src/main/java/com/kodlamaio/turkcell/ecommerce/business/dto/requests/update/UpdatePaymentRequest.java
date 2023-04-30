package com.kodlamaio.turkcell.ecommerce.business.dto.requests.update;

import com.kodlamaio.turkcell.ecommerce.entities.concretes.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Dictionary;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdatePaymentRequest {
    @NotNull
    @Min(value = 1)
    private double balance;
}
