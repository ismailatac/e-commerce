package com.kodlamaio.turkcell.ecommerce.api.controllers;


import com.kodlamaio.turkcell.ecommerce.business.abstracts.ProductService;
import com.kodlamaio.turkcell.ecommerce.entities.concretes.Product;

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
    public List<Product> getAll(){
        return service.getAll();
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        service.delete(id);
    }

    @PutMapping("/{id}")
    public Product update(@RequestBody Product p, @PathVariable int id){
        return service.update(id,p);
    }
    @GetMapping("/{id}")
    public Product getById(@PathVariable int id){
       return service.getById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody Product p){
        service.add(p);
    }




//    @GetMapping("/")
//    public Product getByIdParam(@RequestParam int id){
//        return service.getById(id);
//    }




}
