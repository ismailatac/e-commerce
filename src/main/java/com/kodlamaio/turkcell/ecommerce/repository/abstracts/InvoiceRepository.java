package com.kodlamaio.turkcell.ecommerce.repository.abstracts;

import com.kodlamaio.turkcell.ecommerce.entities.concretes.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    
}
