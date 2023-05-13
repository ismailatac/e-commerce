package com.kodlamaio.turkcell.ecommerce.business.abstracts;

import com.kodlamaio.turkcell.ecommerce.business.dto.requests.create.CreateProductRequest;
import com.kodlamaio.turkcell.ecommerce.business.dto.requests.update.UpdateProductRequest;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.create.CreateProductResponse;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.get.GetAllProductsResponse;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.get.GetProductResponse;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.update.UpdateProductResponse;
import com.kodlamaio.turkcell.ecommerce.entities.concretes.Product;
import com.kodlamaio.turkcell.ecommerce.entities.enums.State;

import java.util.List;

public interface ProductService {

    List<GetAllProductsResponse> getAll(boolean isActive);
    CreateProductResponse add(CreateProductRequest request);
    void delete(int id);
    UpdateProductResponse update(int id, UpdateProductRequest request);
    GetProductResponse getById(int id);
    void changeProductState(int id, State state);
    Product getByProductName(String productName);
}
