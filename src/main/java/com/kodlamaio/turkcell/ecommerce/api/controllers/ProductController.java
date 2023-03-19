package com.kodlamaio.turkcell.ecommerce.api.controllers;


import com.kodlamaio.turkcell.ecommerce.business.abstracts.ProductService;
import com.kodlamaio.turkcell.ecommerce.entities.concretes.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/getAll")
    public List<Product> getAll(){
        return service.getAll();
    }
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable int id){
        return service.delete(id);
    }

    @PutMapping("/update")
    public boolean update(@RequestBody Product p){
        return service.update(p);
    }
    @GetMapping("/{id}")
    public Product getById(@PathVariable int id){
       return service.getById(id);
    }
    @PostMapping("/add")
    public void add(@RequestBody Product p){
        service.add(p);
    }


}
