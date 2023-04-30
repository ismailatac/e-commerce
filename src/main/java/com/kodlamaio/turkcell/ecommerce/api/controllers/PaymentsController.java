package com.kodlamaio.turkcell.ecommerce.api.controllers;

import com.kodlamaio.turkcell.ecommerce.business.abstracts.PaymentService;
import com.kodlamaio.turkcell.ecommerce.business.dto.requests.create.CreatePaymentRequest;
import com.kodlamaio.turkcell.ecommerce.business.dto.requests.update.UpdatePaymentRequest;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.create.CreatePaymentResponse;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.get.GetAllPaymentsResponse;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.get.GetPaymentResponse;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.update.UpdatePaymentResponse;
import com.kodlamaio.turkcell.ecommerce.entities.enums.State;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@AllArgsConstructor
public class PaymentsController {

    private final PaymentService service;

    @GetMapping
    public List<GetAllPaymentsResponse> getAll(){
        return service.getAll();
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        service.delete(id);
    }

    @PutMapping("/{id}")
    public UpdatePaymentResponse update(@RequestBody UpdatePaymentRequest payment, @PathVariable int id){
        return service.update(id,payment);
    }
    @GetMapping("/{id}")
    public GetPaymentResponse getById(@PathVariable int id){
       return service.getById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatePaymentResponse add(@RequestBody CreatePaymentRequest payment){
        return service.add(payment);
    }






//    @GetMapping("/")
//    public Payment getByIdParam(@RequestParam int id){
//        return service.getById(id);
//    }




}
