package com.kodlamaio.turkcell.ecommerce.repository.abstracts;


import com.kodlamaio.turkcell.ecommerce.entities.concretes.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale,Integer> {



}
