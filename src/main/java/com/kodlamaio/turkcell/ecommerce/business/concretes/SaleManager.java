package com.kodlamaio.turkcell.ecommerce.business.concretes;

import com.kodlamaio.turkcell.ecommerce.business.abstracts.InvoiceService;
import com.kodlamaio.turkcell.ecommerce.business.abstracts.PaymentService;
import com.kodlamaio.turkcell.ecommerce.business.abstracts.ProductService;
import com.kodlamaio.turkcell.ecommerce.business.abstracts.SaleService;
import com.kodlamaio.turkcell.ecommerce.business.dto.requests.create.CreateInvoiceRequest;
import com.kodlamaio.turkcell.ecommerce.business.dto.requests.create.CreateSaleRequest;
import com.kodlamaio.turkcell.ecommerce.business.dto.requests.update.UpdateProductRequest;
import com.kodlamaio.turkcell.ecommerce.business.dto.requests.update.UpdateSaleRequest;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.create.CreateSaleResponse;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.get.GetAllSalesResponse;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.get.GetProductResponse;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.get.GetSaleResponse;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.update.UpdateSaleResponse;
import com.kodlamaio.turkcell.ecommerce.business.rules.SaleBusinessRules;
import com.kodlamaio.turkcell.ecommerce.common.dto.CreateSalePaymentRequest;
import com.kodlamaio.turkcell.ecommerce.core.exceptions.BusinessException;
import com.kodlamaio.turkcell.ecommerce.entities.concretes.Product;
import com.kodlamaio.turkcell.ecommerce.entities.concretes.Sale;
import com.kodlamaio.turkcell.ecommerce.entities.enums.State;
import com.kodlamaio.turkcell.ecommerce.repository.abstracts.SaleRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class SaleManager implements SaleService {

    private final SaleRepository repository;
    private final ModelMapper mapper;
    private final SaleBusinessRules rules;
    private final ProductService productService;
    private final PaymentService paymentService;
    private final InvoiceService invoiceService;


    @Override
    public List<GetAllSalesResponse> getAll() {
        List<Sale> sales = repository.findAll();
        List<GetAllSalesResponse> responseList = sales.stream()
                .map(sale -> mapper
                        .map(sale, GetAllSalesResponse.class)).toList();
        return responseList;
    }

    @Override
    public CreateSaleResponse add(CreateSaleRequest request) {
        checkProductAvailabilityForSale(request);
        Sale sale = mapper.map(request, Sale.class);
        sale.setDate(LocalDateTime.now());

        GetProductResponse product = productService.getById(sale.getProductId());
        CreateSalePaymentRequest paymentRequest = new CreateSalePaymentRequest();
        mapper.map(request.getPaymentRequest(), paymentRequest);
        paymentRequest.setPrice(getTotalPrice(product, sale.getQuantityToBeSold()));
        paymentService.processSalePayment(paymentRequest);

        sale.setTotalPrice(getTotalPrice(product, sale.getQuantityToBeSold()));
        repository.save(sale);
        reduceQuantityProducts(product.getId());
        CreateSaleResponse response = mapper.map(sale, CreateSaleResponse.class);

        //TODO: sale için invoice
        CreateInvoiceRequest invoiceRequest = new CreateInvoiceRequest();
        createInvoiceRequest(request, invoiceRequest, sale);
        invoiceService.add(invoiceRequest);

        return response;
    }

    @Override
    public void delete(int id) {
        rules.checkIfSaleExists(id);
        repository.deleteById(id);
    }

    @Override
    public UpdateSaleResponse update(int id, UpdateSaleRequest updateSaleRequest) {
        rules.checkIfSaleExists(id);
        Sale sale = mapper.map(updateSaleRequest, Sale.class);
        GetProductResponse product = productService.getById(sale.getProductId());
        sale.setId(id);
        sale.setTotalPrice(getTotalPrice(product, sale.getQuantityToBeSold()));
        repository.save(sale);
        UpdateSaleResponse response = mapper.map(sale, UpdateSaleResponse.class);
        return response;
    }

    @Override
    public GetSaleResponse getById(int id) {
        rules.checkIfSaleExists(id);
        Sale sale = repository.findById(id).orElseThrow();
        GetSaleResponse response = mapper.map(sale, GetSaleResponse.class);
        return response;
    }

    private void checkProductAvailabilityForSale(CreateSaleRequest request) {
        var product = productService.getById(request.getProductId());
        if (product.getIsActive().equals(State.PASSIVE)) {
            throw new BusinessException("Ürün pasif");
        }
        if (product.getQuantity() < 0) {
            throw new BusinessException("Ürün stokta yok");
        }
    }

    private double getTotalPrice(GetProductResponse product, int quantity) {
        return product.getPrice() * quantity;
    }

    private void reduceQuantityProducts(int id) {
        Product product1 = mapper.map(productService.getById(id), Product.class);
        product1.setQuantity(product1.getQuantity() - 1);
        productService.update(product1.getId(), mapper.map(product1, UpdateProductRequest.class));
    }

    private void createInvoiceRequest(CreateSaleRequest request, CreateInvoiceRequest invoiceRequest, Sale sale) {
        var product = productService.getById(request.getProductId());

        invoiceRequest.setCardHolder(request.getPaymentRequest().getCardHolderName());
        invoiceRequest.setDate(sale.getDate());
        invoiceRequest.setProductName(product.getName());
        invoiceRequest.setTotalPrice(getTotalPrice(product, sale.getQuantityToBeSold()));
        invoiceRequest.setQuantityToBeSold(sale.getQuantityToBeSold());


    }

}



