package com.techienotes.lowlatencymicroservice.service;

import com.techienotes.lowlatencymicroservice.model.Price;
import com.techienotes.lowlatencymicroservice.model.PricingData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PricingService {
    void savePrice(Price price);

    long savePricingData();

    List<Price> getPrices();

    PricingData getPricingData(long id);
}
