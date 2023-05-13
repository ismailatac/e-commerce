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
public class UpdateSaleResponse {
    private int id;
    private double totalPrice;
    private LocalDateTime date;
    private int quantityToBeSold;
}
