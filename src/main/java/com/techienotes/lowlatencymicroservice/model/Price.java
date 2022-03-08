package com.techienotes.lowlatencymicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Price implements Serializable {
    double bid;
    double ask;
    double spot;
    String currencyPair;
}
