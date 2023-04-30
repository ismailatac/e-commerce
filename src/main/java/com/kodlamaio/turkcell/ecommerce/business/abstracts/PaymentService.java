package com.kodlamaio.turkcell.ecommerce.business.abstracts;

import com.kodlamaio.turkcell.ecommerce.business.dto.requests.create.CreatePaymentRequest;
import com.kodlamaio.turkcell.ecommerce.business.dto.requests.update.UpdatePaymentRequest;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.create.CreatePaymentResponse;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.get.GetAllPaymentsResponse;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.get.GetPaymentResponse;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.update.UpdatePaymentResponse;
import com.kodlamaio.turkcell.ecommerce.common.dto.CreateSalePaymentRequest;

import java.util.List;

public interface PaymentService {

    List<GetAllPaymentsResponse> getAll();
    CreatePaymentResponse add(CreatePaymentRequest request);
    void delete(int id);
    UpdatePaymentResponse update(int id, UpdatePaymentRequest request);
    GetPaymentResponse getById(int id);
    void processSalePayment(CreateSalePaymentRequest request);

}
