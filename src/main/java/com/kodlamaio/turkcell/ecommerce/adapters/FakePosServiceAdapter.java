package com.kodlamaio.turkcell.ecommerce.adapters;

import com.kodlamaio.turkcell.ecommerce.business.abstracts.PosService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class FakePosServiceAdapter implements PosService {
    @Override
    public void pay() {
        boolean isPaymentSuccessfull = new Random().nextBoolean();
        if (!isPaymentSuccessfull) throw new RuntimeException("Ödeme reddedildi!");
    }
}
