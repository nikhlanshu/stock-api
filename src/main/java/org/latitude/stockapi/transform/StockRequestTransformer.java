package org.latitude.stockapi.transform;

import org.javamoney.moneta.Money;
import org.latitude.stockapi.dto.StockRequest;

import javax.money.MonetaryAmount;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.AbstractMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;

import static org.latitude.stockapi.validator.StartDateEndDateValidator.isEndDateTimeIsBeforeStartDateTime;

public interface StockRequestTransformer {
    static SortedSet<Map.Entry<LocalDateTime, MonetaryAmount>> initSockAndReturn(StockRequest stockRequest) {
        LocalDateTime startDateTime = convertToLocalDateTime(stockRequest.getStock().getStartDateTime());
        LocalDateTime endDateTime = convertToLocalDateTime(stockRequest.getStock().getEndDateTime());
        isEndDateTimeIsBeforeStartDateTime(startDateTime, endDateTime);
        AtomicInteger hourCount = new AtomicInteger(0);
        SortedSet<Map.Entry<LocalDateTime, MonetaryAmount>> sortedStockPrices = new TreeSet<>(Map.Entry.comparingByValue());
        stockRequest.getStock().getStockPrices()
                .forEach(price -> {
                    LocalDateTime key = startDateTime.plusHours(hourCount.getAndIncrement());
                    MonetaryAmount value = Money.of(price, "AUD");
                    sortedStockPrices.add(new AbstractMap.SimpleEntry<>(key, value));
                });
        return sortedStockPrices;
    }

    static LocalDateTime convertToLocalDateTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(dateTime, formatter);
    }
}
