package com.kodlamaio.turkcell.ecommerce.business.dto.responses.get;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetSaleResponse {
    private int id;
    private double totalPrice;
    private LocalDateTime date;
    private int quantityToBeSold;
}
