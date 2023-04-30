package com.kodlamaio.turkcell.ecommerce.business.abstracts;

import com.kodlamaio.turkcell.ecommerce.business.dto.requests.create.CreateSaleRequest;
import com.kodlamaio.turkcell.ecommerce.business.dto.requests.update.UpdateSaleRequest;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.create.CreateSaleResponse;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.get.GetAllSalesResponse;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.get.GetSaleResponse;
import com.kodlamaio.turkcell.ecommerce.business.dto.responses.update.UpdateSaleResponse;
import com.kodlamaio.turkcell.ecommerce.entities.enums.State;

import java.util.List;

public interface SaleService {

    List<GetAllSalesResponse> getAll();
    CreateSaleResponse add(CreateSaleRequest request);
    void delete(int id);
    UpdateSaleResponse update(int id, UpdateSaleRequest request);
    GetSaleResponse getById(int id);

}
