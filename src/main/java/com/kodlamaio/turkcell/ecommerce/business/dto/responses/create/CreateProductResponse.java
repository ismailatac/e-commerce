package com.kodlamaio.turkcell.ecommerce.business.dto.responses.create;

import com.kodlamaio.turkcell.ecommerce.entities.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateProductResponse {
    private int id;
    private String name;
    private int quantity;
    private double price;
    private String description;
    private State isActive;
}
