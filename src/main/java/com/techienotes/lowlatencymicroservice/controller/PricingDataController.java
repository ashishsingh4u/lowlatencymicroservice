package com.techienotes.lowlatencymicroservice.controller;

import com.techienotes.lowlatencymicroservice.model.Price;
import com.techienotes.lowlatencymicroservice.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1")
public class PricingDataController {

    @Autowired
    QueueService queueService;

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }

    @PostMapping("/save")
    public void savePrice(@RequestBody Price price) {
        queueService.savePrice(price);
    }

    @GetMapping("/prices")
    public List<Price> getPrices() {
        return queueService.getPrices();
    }
}
