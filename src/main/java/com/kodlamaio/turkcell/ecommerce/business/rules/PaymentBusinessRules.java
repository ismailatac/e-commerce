package com.kodlamaio.turkcell.ecommerce.business.rules;

import com.kodlamaio.turkcell.ecommerce.common.constants.Messages;
import com.kodlamaio.turkcell.ecommerce.common.dto.CreateSalePaymentRequest;
import com.kodlamaio.turkcell.ecommerce.core.exceptions.BusinessException;
import com.kodlamaio.turkcell.ecommerce.repository.abstracts.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentBusinessRules {
    private final PaymentRepository repository;

    public void checkIfPaymentIsValid(CreateSalePaymentRequest request) {
        if (!repository.existsByCardNumberAndCardHolderNameAndCardExpirationYearAndCardExpirationMonthAndCardCvv(
                request.getCardNumber(),
                request.getCardHolderName(),
                request.getCardExpirationYear(),
                request.getCardExpirationMonth(),
                request.getCardCvv()
        )) {
            throw new BusinessException(Messages.Payment.Incorrect);
        }
    }

    public void checkIfCardExists(String cardNumber) {
        if (repository.existsByCardNumber(cardNumber)) {
            throw new BusinessException(Messages.Payment.AlreadyExists);
        }
    }

    public void checkIfBalanceIdEnough(double balance, double price) {
        if (balance < price) {
            throw new BusinessException(Messages.Payment.NotEnoughBalance);
        }
    }

    public void checkIfPaymentNotExists(int id) {
        if (!repository.existsById(id)) {
            throw new BusinessException(Messages.Payment.NotFound);
        }
    }
}
