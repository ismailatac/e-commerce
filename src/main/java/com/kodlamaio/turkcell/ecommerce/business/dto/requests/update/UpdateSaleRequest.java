package com.kodlamaio.turkcell.ecommerce.business.dto.requests.update;

import com.kodlamaio.turkcell.ecommerce.entities.concretes.Product;
import com.kodlamaio.turkcell.ecommerce.entities.enums.State;
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
public class UpdateSaleRequest {
    private LocalDateTime date;
    private Dictionary<Product,Integer> productsAndCounts;
}
