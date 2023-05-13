package com.kodlamaio.turkcell.ecommerce.business.concretes;

import com.kodlamaio.turkcell.ecommerce.business.abstracts.PaymentService;
import com.kodlamaio.turkcell.ecommerce.business.abstracts.PosService;
import com.kodlamaio.turkcell.ecommerce.business.dto.requests.create.CreatePaymentRequest;
import com.kodlamaio.turkcell.ecommerce.business.dto.requests.update.UpdatePaymentRequest;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.create.CreatePaymentResponse;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.get.GetAllPaymentsResponse;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.get.GetPaymentResponse;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.update.UpdatePaymentResponse;
import com.kodlamaio.turkcell.ecommerce.business.rules.PaymentBusinessRules;
import com.kodlamaio.turkcell.ecommerce.common.dto.CreateSalePaymentRequest;
import com.kodlamaio.turkcell.ecommerce.entities.concretes.Payment;
import com.kodlamaio.turkcell.ecommerce.repository.abstracts.PaymentRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PaymentManager implements PaymentService {

    private final PaymentRepository repository;
    private final ModelMapper mapper;
    private final PosService posService;
    private final PaymentBusinessRules rules;

    @Override
    public List<GetAllPaymentsResponse> getAll() {
        List<Payment> payments = repository.findAll();
        List<GetAllPaymentsResponse> response = payments.stream()
                .map(payment -> mapper.map(payment, GetAllPaymentsResponse.class)).toList();
        return response;
    }

    @Override
    public CreatePaymentResponse add(CreatePaymentRequest request) {
        rules.checkIfCardExists(request.getCardNumber());
        Payment payment = mapper.map(request, Payment.class);
        payment.setId(0);
        repository.save(payment);
        CreatePaymentResponse response = mapper.map(payment, CreatePaymentResponse.class);
        return response;
    }


    @Override
    public void delete(int id) {
        rules.checkIfPaymentNotExists(id);
        repository.deleteById(id);
    }

    @Override
    public UpdatePaymentResponse update(int id, UpdatePaymentRequest request) {
        rules.checkIfPaymentNotExists(id);
        Payment payment = mapper.map(request, Payment.class);
        payment.setId(id);
        repository.save(payment);
        UpdatePaymentResponse response = mapper.map(payment, UpdatePaymentResponse.class);
        return response;
    }

    @Override
    public GetPaymentResponse getById(int id) {
        rules.checkIfPaymentNotExists(id);
        Payment payment = repository.findById(id).orElseThrow();
        GetPaymentResponse response = mapper.map(payment, GetPaymentResponse.class);
        return response;
    }

    @Override
    public void processSalePayment(CreateSalePaymentRequest request) {
        rules.checkIfPaymentIsValid(request);
        Payment payment = repository.findByCardNumber(request.getCardNumber());
        rules.checkIfBalanceIdEnough(payment.getBalance(), request.getPrice());
        posService.pay();//fake pos service
        payment.setBalance(payment.getBalance() - request.getPrice());
        repository.save(payment);
    }


}
