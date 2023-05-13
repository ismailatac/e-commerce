package com.kodlamaio.turkcell.ecommerce.business.dto.responses.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateInvoiceResponse {
    private int id;
    private String cardHolder;
    private double totalPrice;
    private String productName;
    private int quantityToBeSold;
    private LocalDateTime date;
}
