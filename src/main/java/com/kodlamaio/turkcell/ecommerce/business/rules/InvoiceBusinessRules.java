package com.kodlamaio.turkcell.ecommerce.business.rules;

import com.kodlamaio.turkcell.ecommerce.common.constants.Messages;
import com.kodlamaio.turkcell.ecommerce.core.exceptions.BusinessException;
import com.kodlamaio.turkcell.ecommerce.repository.abstracts.InvoiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InvoiceBusinessRules {
    private final InvoiceRepository repository;

    public void checkIfInvoiceExists(int id) {
        if (!repository.existsById(id)) throw new BusinessException(Messages.Invoice.NotExists);
    }
}
