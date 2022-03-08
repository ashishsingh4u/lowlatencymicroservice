package com.techienotes.lowlatencymicroservice.service;

import com.techienotes.lowlatencymicroservice.helper.QueueHelperService;
import com.techienotes.lowlatencymicroservice.model.Price;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class LowLatencyQueueService implements QueueService {

    @Autowired
    QueueHelperService queueHelperService;

    @Override
    public void savePrice(Price price) {
        queueHelperService.saveObject(price);
    }

    @Override
    public List<Price> getPrices() {
        return queueHelperService.getObjects();
    }
}
