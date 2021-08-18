package org.latitude.stockapi.service;

import org.latitude.stockapi.dto.StockRequest;
import org.latitude.stockapi.dto.StockResponse;
import org.latitude.stockapi.transform.StockRequestTransformer;
import org.latitude.stockapi.transform.StockResponseTransformer;
import org.springframework.stereotype.Service;

import javax.money.MonetaryAmount;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.SortedSet;

@Service
public class DefaultStockService implements StockService {

    public StockResponse processStock(StockRequest stockRequest) {
        SortedSet<Map.Entry<LocalDateTime, MonetaryAmount>> sortedStockEntries = StockRequestTransformer.initSockAndReturn(stockRequest);
        StockResponse stockResponse = StockResponseTransformer.of(sortedStockEntries);
        stockResponse.setStock(stockRequest.getStock());
        return stockResponse;
    }
}
