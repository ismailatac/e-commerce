package com.kodlamaio.turkcell.ecommerce.business.dto.requests.update;

import com.kodlamaio.turkcell.ecommerce.entities.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateProductRequest {
    private String name;
    private int quantity;
    private double price;
    private String description;
    private State state;
}
