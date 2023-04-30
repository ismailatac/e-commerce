package com.kodlamaio.turkcell.ecommerce.repository.abstracts;


import com.kodlamaio.turkcell.ecommerce.entities.concretes.Payment;
import com.kodlamaio.turkcell.ecommerce.entities.concretes.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Integer> {
    Payment findByCardNumber(String cardNumber);

    boolean existsByCardNumber(String cardNumber);

    boolean existsByCardNumberAndCardHolderNameAndCardExpirationYearAndCardExpirationMonthAndCardCvv(
            String cardNumber, String cardHolderName, int cardExpirationYear, int cardExpirationMonth, String cardCvv
    );


}
