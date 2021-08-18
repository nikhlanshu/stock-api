package org.latitude.stockapi.service;

import org.latitude.stockapi.dto.StockRequest;
import org.latitude.stockapi.dto.StockResponse;

@FunctionalInterface
public interface StockService {
    StockResponse processStock(StockRequest stockRequest);
}
