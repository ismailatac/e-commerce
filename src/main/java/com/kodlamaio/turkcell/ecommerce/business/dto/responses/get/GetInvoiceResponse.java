package com.kodlamaio.turkcell.ecommerce.business.dto.responses.get;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetInvoiceResponse {
    private int id;
    private String cardHolder;
    private double totalPrice;
    private String productName;
    private int quantityToBeSold;
    private LocalDateTime date;
}
