package com.techienotes.lowlatencymicroservice.service;

import com.techienotes.lowlatencymicroservice.model.Price;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QueueService {
    void savePrice(Price price);

    List<Price> getPrices();
}
