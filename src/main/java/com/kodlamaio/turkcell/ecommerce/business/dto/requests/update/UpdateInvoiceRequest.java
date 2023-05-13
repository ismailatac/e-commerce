package com.kodlamaio.turkcell.ecommerce.business.dto.requests.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateInvoiceRequest {
    private String cardHolder;
    private double totalPrice;
    private String productName;
    private int quantityToBeSold;
    private LocalDateTime date;
}
