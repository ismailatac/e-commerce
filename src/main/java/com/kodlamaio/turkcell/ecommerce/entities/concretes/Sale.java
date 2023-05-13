package com.kodlamaio.turkcell.ecommerce.entities.concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sales")
public class Sale {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private double totalPrice;
    private LocalDateTime date;
    private int quantityToBeSold;
    @OneToOne(mappedBy = "sale")
    private Product product;

}
