package com.kodlamaio.turkcell.ecommerce.business.abstracts;

import com.kodlamaio.turkcell.ecommerce.business.dto.requests.create.CreateProductRequest;
import com.kodlamaio.turkcell.ecommerce.business.dto.requests.update.UpdateProductRequest;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.create.CreateProductResponse;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.get.GetAllProductsResponse;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.get.GetProductResponse;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.update.UpdateProductResponse;

import java.util.List;

public interface ProductService {

    List<GetAllProductsResponse> getAll();
    CreateProductResponse add(CreateProductRequest p);
    void delete(int id);
    UpdateProductResponse update(int id, UpdateProductRequest updateProductRequest);
    GetProductResponse getById(int id);
}
