package com.kodlamaio.turkcell.ecommerce.api.controllers;

import com.kodlamaio.turkcell.ecommerce.business.abstracts.SaleService;
import com.kodlamaio.turkcell.ecommerce.business.dto.requests.create.CreateSaleRequest;
import com.kodlamaio.turkcell.ecommerce.business.dto.requests.update.UpdateSaleRequest;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.create.CreateSaleResponse;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.get.GetAllSalesResponse;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.get.GetSaleResponse;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.update.UpdateSaleResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
@AllArgsConstructor
public class SalesController {

    private final SaleService service;

    @GetMapping
    public List<GetAllSalesResponse> getAll(){
        return service.getAll();
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        service.delete(id);
    }

    @PutMapping("/{id}")
    public UpdateSaleResponse update(@RequestBody UpdateSaleRequest sale, @PathVariable int id){
        return service.update(id,sale);
    }
    @GetMapping("/{id}")
    public GetSaleResponse getById(@PathVariable int id){
       return service.getById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateSaleResponse add(@RequestBody CreateSaleRequest sale){
        return service.add(sale);
    }




}
