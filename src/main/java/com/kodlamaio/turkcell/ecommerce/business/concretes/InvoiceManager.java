package com.kodlamaio.turkcell.ecommerce.business.concretes;

import com.kodlamaio.turkcell.ecommerce.business.abstracts.InvoiceService;
import com.kodlamaio.turkcell.ecommerce.business.abstracts.ProductService;
import com.kodlamaio.turkcell.ecommerce.business.dto.requests.create.CreateInvoiceRequest;
import com.kodlamaio.turkcell.ecommerce.business.dto.requests.update.UpdateInvoiceRequest;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.create.CreateInvoiceResponse;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.get.GetAllInvoicesResponse;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.get.GetInvoiceResponse;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.update.UpdateInvoiceResponse;
import com.kodlamaio.turkcell.ecommerce.business.rules.InvoiceBusinessRules;
import com.kodlamaio.turkcell.ecommerce.entities.concretes.Invoice;
import com.kodlamaio.turkcell.ecommerce.entities.concretes.Product;
import com.kodlamaio.turkcell.ecommerce.repository.abstracts.InvoiceRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InvoiceManager implements InvoiceService {

    private final InvoiceRepository repository;
    private final ModelMapper mapper;
    private final ProductService productService;
    private final InvoiceBusinessRules rules;

    @Override
    public List<GetAllInvoicesResponse> getAll() {
        List<Invoice> invoices = repository.findAll();
        List<GetAllInvoicesResponse> response = invoices
                .stream()
                .map(invoice -> mapper.map(invoice, GetAllInvoicesResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetInvoiceResponse getById(int id) {
        rules.checkIfInvoiceExists(id);
        Invoice invoice = repository.findById(id).orElseThrow();
        GetInvoiceResponse response = mapper.map(invoice, GetInvoiceResponse.class);
        return response;
    }

    @Override
    public CreateInvoiceResponse add(CreateInvoiceRequest request) {
        Invoice invoice = mapper.map(request, Invoice.class);
        invoice.setId(0);
        invoice.setTotalPrice(getTotalPrice(productService.getByProductName(invoice.getProductName()), invoice.getQuantityToBeSold()));
        repository.save(invoice);
        CreateInvoiceResponse response = mapper.map(invoice, CreateInvoiceResponse.class);
        return response;
    }

    @Override
    public UpdateInvoiceResponse update(int id, UpdateInvoiceRequest request) {
        rules.checkIfInvoiceExists(id);
        Invoice invoice = mapper.map(request, Invoice.class);
        invoice.setId(id);
        invoice.setTotalPrice(getTotalPrice(productService.getByProductName(invoice.getProductName()), invoice.getQuantityToBeSold()));
        repository.save(invoice);
        UpdateInvoiceResponse response = mapper.map(invoice, UpdateInvoiceResponse.class);
        return response;
    }

    @Override
    public void delete(int id) {
        rules.checkIfInvoiceExists(id);
        repository.deleteById(id);
    }

    private double getTotalPrice(Product product, int quantity) {
        return product.getPrice() * quantity;
    }
}
