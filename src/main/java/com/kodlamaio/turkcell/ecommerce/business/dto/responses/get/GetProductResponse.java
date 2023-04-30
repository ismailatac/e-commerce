package com.kodlamaio.turkcell.ecommerce.business.dto.responses.get;

import com.kodlamaio.turkcell.ecommerce.entities.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetProductResponse {
    private int id;
    private String name;
    private int quantity;
    private double price;
    private String description;
    private State isActive;
}
