package com.kodlamaio.turkcell.ecommerce.business.concretes;

import com.kodlamaio.turkcell.ecommerce.business.abstracts.PaymentService;
import com.kodlamaio.turkcell.ecommerce.business.abstracts.ProductService;
import com.kodlamaio.turkcell.ecommerce.business.abstracts.SaleService;
import com.kodlamaio.turkcell.ecommerce.business.dto.requests.create.CreateSaleRequest;
import com.kodlamaio.turkcell.ecommerce.business.dto.requests.update.UpdateSaleRequest;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.create.CreateSaleResponse;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.get.GetAllSalesResponse;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.get.GetSaleResponse;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.update.UpdateSaleResponse;
import com.kodlamaio.turkcell.ecommerce.common.dto.CreateSalePaymentRequest;
import com.kodlamaio.turkcell.ecommerce.core.exceptions.BusinessException;
import com.kodlamaio.turkcell.ecommerce.entities.concretes.Product;
import com.kodlamaio.turkcell.ecommerce.entities.concretes.Sale;
import com.kodlamaio.turkcell.ecommerce.entities.enums.State;
import com.kodlamaio.turkcell.ecommerce.repository.abstracts.SaleRepository;
import com.kodlamaio.turkcell.ecommerce.rules.SaleBusinessRules;
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
        checkCarAvailabilityForSale(request);
        Sale sale = mapper.map(request,Sale.class);
        sale.setDate(LocalDateTime.now());

        CreateSalePaymentRequest paymentRequest = new CreateSalePaymentRequest();
        mapper.map(request.getPaymentRequest(), paymentRequest);
        paymentRequest.setPrice(getTotalPrice(sale.getProducts()));
        paymentService.processSalePayment(paymentRequest);

        sale.setTotalPrice(getTotalPrice(sale.getProducts()));
        repository.save(sale);
        reduceQuantityProducts(sale.getProducts());
        CreateSaleResponse response = mapper.map(sale,CreateSaleResponse.class);

        //TODO: sale için invoice
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
        Sale sale = mapper.map(updateSaleRequest,Sale.class);
        sale.setId(id);
        sale.setTotalPrice(getTotalPrice(sale.getProducts()));
        repository.save(sale);
        UpdateSaleResponse response = mapper.map(sale,UpdateSaleResponse.class);
        return response;
    }

    @Override
    public GetSaleResponse getById(int id) {
        rules.checkIfSaleExists(id);
        Sale sale = repository.findById(id).orElseThrow();
        GetSaleResponse response = mapper.map(sale,GetSaleResponse.class);
        return response;
    }
    private void checkCarAvailabilityForSale(CreateSaleRequest request) {
        for(Product product: request.getProducts()) {
            if (!productService.getById(product.getId()).getIsActive().equals(State.ACTIVE)) {
                throw new BusinessException("Satışa müsait değil!");
            }
        }
    }
    private double getTotalPrice(List<Product> products) {
        double sum=0;
        for (Product product : products){
            sum+=product.getPrice();
    }
        return sum;
    }
    private void reduceQuantityProducts(List<Product> products) {
        for (Product product:products){
            product.setQuantity(product.getQuantity()-1);
        }
    }

}



