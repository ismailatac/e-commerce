package com.kodlamaio.turkcell.ecommerce.entities.concretes;

import com.kodlamaio.turkcell.ecommerce.entities.enums.State;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private String name;
    private int quantity;
    private double price;
    private String description;
    @Enumerated(EnumType.STRING)
    private State isActive;
    @OneToOne
    @JoinColumn(name = "sale_id")
    private Sale sale;
    @OneToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;




}
