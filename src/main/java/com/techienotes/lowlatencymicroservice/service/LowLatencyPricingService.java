package com.techienotes.lowlatencymicroservice.service;

import com.techienotes.lowlatencymicroservice.helper.MapHelperService;
import com.techienotes.lowlatencymicroservice.helper.QueueHelperService;
import com.techienotes.lowlatencymicroservice.model.Price;
import com.techienotes.lowlatencymicroservice.model.PricingData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class LowLatencyPricingService implements PricingService {

    @Autowired
    QueueHelperService queueHelperService;

    @Autowired
    MapHelperService mapHelperService;

    @Override
    public void savePrice(Price price) {
        queueHelperService.saveObject(price);
    }

    @Override
    public long savePricingData() {
        return mapHelperService.saveObject();
    }

    @Override
    public List<Price> getPrices() {
        return queueHelperService.getObjects();
    }

    @Override
    public PricingData getPricingData(long id) {
        return mapHelperService.getObject(id);
    }
}
