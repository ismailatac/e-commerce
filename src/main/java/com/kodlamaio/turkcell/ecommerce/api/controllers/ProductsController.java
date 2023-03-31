package com.kodlamaio.turkcell.ecommerce.api.controllers;

import com.kodlamaio.turkcell.ecommerce.business.abstracts.ProductService;
import com.kodlamaio.turkcell.ecommerce.business.dto.requests.create.CreateProductRequest;
import com.kodlamaio.turkcell.ecommerce.business.dto.requests.update.UpdateProductRequest;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.create.CreateProductResponse;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.get.GetAllProductsResponse;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.get.GetProductResponse;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.update.UpdateProductResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductsController {

    private final ProductService service;

    @GetMapping
    public List<GetAllProductsResponse> getAll(){
        return service.getAll();
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        service.delete(id);
    }

    @PutMapping("/{id}")
    public UpdateProductResponse update(@RequestBody UpdateProductRequest product, @PathVariable int id){
        return service.update(id,product);
    }
    @GetMapping("/{id}")
    public GetProductResponse getById(@PathVariable int id){
       return service.getById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateProductResponse add(@RequestBody CreateProductRequest product){
        return service.add(product);
    }




//    @GetMapping("/")
//    public Product getByIdParam(@RequestParam int id){
//        return service.getById(id);
//    }




}
