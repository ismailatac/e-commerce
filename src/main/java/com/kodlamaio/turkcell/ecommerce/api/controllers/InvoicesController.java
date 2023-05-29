package com.kodlamaio.turkcell.ecommerce.api.controllers;


import com.kodlamaio.turkcell.ecommerce.business.abstracts.InvoiceService;
import com.kodlamaio.turkcell.ecommerce.business.dto.requests.create.CreateInvoiceRequest;
import com.kodlamaio.turkcell.ecommerce.business.dto.requests.update.UpdateInvoiceRequest;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.create.CreateInvoiceResponse;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.get.GetAllInvoicesResponse;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.get.GetInvoiceResponse;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.update.UpdateInvoiceResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/invoices")
public class InvoicesController {
    private final InvoiceService service;

    @GetMapping
    public List<GetAllInvoicesResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetInvoiceResponse getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateInvoiceResponse add(@RequestBody CreateInvoiceRequest request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdateInvoiceResponse update(@PathVariable int id, @RequestBody UpdateInvoiceRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}