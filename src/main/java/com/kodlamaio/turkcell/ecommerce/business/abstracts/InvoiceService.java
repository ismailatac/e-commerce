package com.kodlamaio.turkcell.ecommerce.business.abstracts;

import com.kodlamaio.turkcell.ecommerce.business.dto.requests.create.CreateInvoiceRequest;
import com.kodlamaio.turkcell.ecommerce.business.dto.requests.update.UpdateInvoiceRequest;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.create.CreateInvoiceResponse;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.get.GetAllInvoicesResponse;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.get.GetInvoiceResponse;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.update.UpdateInvoiceResponse;

import java.util.List;

public interface InvoiceService {
    List<GetAllInvoicesResponse> getAll();

    GetInvoiceResponse getById(int id);

    CreateInvoiceResponse add(CreateInvoiceRequest request);

    UpdateInvoiceResponse update(int id, UpdateInvoiceRequest request);

    void delete(int id);
}
