package com.kodlamaio.turkcell.ecommerce.business.dto.responses.get;

import com.kodlamaio.turkcell.ecommerce.entities.concretes.Product;
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
public class GetSaleResponse {
    private int id;
    private double totalPrice;
    private LocalDateTime date;
    private Dictionary<Product,Integer> productsAndCounts;
}
