package org.latitude.stockapi.transform;

import org.javamoney.moneta.Money;
import org.latitude.stockapi.dto.StockResponse;

import javax.money.MonetaryAmount;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.SortedSet;

public interface StockResponseTransformer {

    static StockResponse of(SortedSet<Map.Entry<LocalDateTime, MonetaryAmount>> sortedStockEntries) {
        MonetaryAmount bestBuyValue = sortedStockEntries.first().getValue();
        MonetaryAmount bestSellValue = sortedStockEntries.last().getValue();
        StockResponse stockResponse = new StockResponse();
        stockResponse.setBuyValue(bestBuyValue.toString());
        stockResponse.setSellValue(bestSellValue.toString());
        stockResponse.setMaxProfit(calculateMaxProfit(bestBuyValue, bestSellValue).toString());
        stockResponse.setProcessedDateTime(sortedStockEntries.last().getKey().toString());
        return stockResponse;
    }

    static MonetaryAmount calculateMaxProfit(MonetaryAmount bestBuyValue, MonetaryAmount bestSellValue) {
        int profit = bestSellValue.getNumber().intValue() - bestBuyValue.getNumber().intValue();
        return Money.of(profit, "AUD");
    }
}
