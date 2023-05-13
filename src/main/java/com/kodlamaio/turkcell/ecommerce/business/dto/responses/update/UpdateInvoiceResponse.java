package com.kodlamaio.turkcell.ecommerce.business.dto.responses.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateInvoiceResponse {
    private int id;
    private String cardHolder;
    private double totalPrice;
    private String productName;
    private int quantityToBeSold;
    private LocalDateTime date;
}
