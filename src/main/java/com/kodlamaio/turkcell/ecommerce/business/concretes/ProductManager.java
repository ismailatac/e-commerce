package com.kodlamaio.turkcell.ecommerce.business.concretes;

import com.kodlamaio.turkcell.ecommerce.business.abstracts.ProductService;
import com.kodlamaio.turkcell.ecommerce.business.dto.requests.create.CreateProductRequest;
import com.kodlamaio.turkcell.ecommerce.business.dto.requests.update.UpdateProductRequest;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.create.CreateProductResponse;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.get.GetAllProductsResponse;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.get.GetProductResponse;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.update.UpdateProductResponse;
import com.kodlamaio.turkcell.ecommerce.entities.concretes.Product;
import com.kodlamaio.turkcell.ecommerce.entities.enums.State;
import com.kodlamaio.turkcell.ecommerce.repository.abstracts.ProductRepository;
import com.kodlamaio.turkcell.ecommerce.rules.ProductBusinessRules;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductManager implements ProductService {

    private final ProductRepository repository;
    private final ModelMapper modelMapper;
    private final ProductBusinessRules rules;



    @Override
    public List<GetAllProductsResponse> getAll(boolean isActive) {
        List<Product> products = rules.filterProductsByisActive(isActive);
        List<GetAllProductsResponse> allProductResponse = products.stream()
                .map(product -> this.modelMapper
                        .map(products, GetAllProductsResponse.class)).toList();
        return allProductResponse;
    }

    @Override
    public CreateProductResponse add(CreateProductRequest createProductRequest) {
        Product product = this.modelMapper.map(createProductRequest,Product.class);
        rules.validateProduct(product);
        product.setIsActive(State.ACTIVE);
        repository.save(product);
        CreateProductResponse response = this.modelMapper.map(product,CreateProductResponse.class);
        return response;
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public UpdateProductResponse update(int id, UpdateProductRequest updateProductRequest) {
        Product product = this.modelMapper.map(updateProductRequest,Product.class);
        rules.validateProduct(product);
        product.setId(id);
        repository.save(product);
        UpdateProductResponse response = this.modelMapper.map(product,UpdateProductResponse.class);
        return response;
    }

    @Override
    public GetProductResponse getById(int id) {
        rules.checkIfProductExist(id);
        Product product = repository.findById(id).orElseThrow();
        GetProductResponse response = this.modelMapper.map(product,GetProductResponse.class);
        return response;
    }

    @Override
    public void changeProductState(int id, State state) {
        rules.checkIfProductExist(id);
        Product product = repository.findById(id).orElseThrow();
        product.setIsActive(state);
        repository.save(product);
    }


}
