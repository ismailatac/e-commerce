package com.kodlamaio.turkcell.ecommerce.business.concretes;

import com.kodlamaio.turkcell.ecommerce.business.abstracts.ProductService;
import com.kodlamaio.turkcell.ecommerce.business.dto.requests.create.CreateProductRequest;
import com.kodlamaio.turkcell.ecommerce.business.dto.requests.update.UpdateProductRequest;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.create.CreateProductResponse;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.get.GetAllProductsResponse;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.get.GetProductResponse;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.update.UpdateProductResponse;
import com.kodlamaio.turkcell.ecommerce.entities.concretes.Product;
import com.kodlamaio.turkcell.ecommerce.repository.abstracts.ProductRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductManager implements ProductService {

    private final ProductRepository repository;
    private final ModelMapper modelMapper;



    @Override
    public List<GetAllProductsResponse> getAll() {

        List<Product> products = repository.findAll();

        List<GetAllProductsResponse> allProductResponse = products.stream()
                .map(product -> this.modelMapper
                        .map(products, GetAllProductsResponse.class)).toList();
        return allProductResponse;
    }

    @Override
    public CreateProductResponse add(CreateProductRequest createProductRequest) {
        Product product = this.modelMapper.map(createProductRequest,Product.class);
        validateProduct(product);
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
        validateProduct(product);
        product.setId(id);
        repository.save(product);
        UpdateProductResponse response = this.modelMapper.map(product,UpdateProductResponse.class);
        return response;
    }

    @Override
    public GetProductResponse getById(int id) {
        checkIfProductExist(id);
        Product product = repository.findById(id).orElseThrow();
        GetProductResponse response = this.modelMapper.map(product,GetProductResponse.class);
        return response;
    }

    private void checkIfProductExist(int id) {
        if(!repository.existsById(id)) throw new RuntimeException("Ürün bulunamadı");
    }

    private  void validateProduct(Product p) {
        checkIfPriceValid(p);
        checkIfQuantityValid(p);
        checkIfDescriptionValid(p);
    }

    private  void checkIfDescriptionValid(Product p) {
        if (p.getDescription().length() < 10 || p.getDescription().length() > 50){
            throw new IllegalArgumentException("Ürün açıklaması 10'dan küçük veya 50 karakterden büyük olmamalı.");
        }
    }

    private  void checkIfQuantityValid(Product p) {
        if (p.getQuantity() < 0){
            throw new IllegalArgumentException("Ürün miktarı sıfırdan küçük olamaz.");
        }
    }

    private  void checkIfPriceValid(Product p) {
        if(p.getPrice() <= 0){
            throw new IllegalArgumentException("Fiyat sıfırdan küçük olamaz.");
        }
    }

}
