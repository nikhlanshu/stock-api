package org.latitude.stockapi.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import org.latitude.stockapi.annotation.StockDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Objects;

@JsonPropertyOrder({"id", "startDateTime","endDateTime", "stockPrices"})
public class Stock {
    @ApiModelProperty(notes = "Unique id", name = "id", required = true, value = "1")
    @NotNull(message = "id can not be null")
    @Positive(message = "id can not be negative or zero")
    private Integer id;

    @ApiModelProperty(notes = "Start date time in yyyy-MM-dd HH:mm format", name = "startDateTime", required = true, value = "2018-12-10 23:12")
    @NotNull(message = "startDateTime can not be null")
    @NotEmpty(message = "startDateTime can not be empty")
    @StockDateTime
    private String startDateTime;

    @ApiModelProperty(notes = "end date time in yyyy-MM-dd HH:mm format", name = "endDateTime", required = true, value = "2018-12-10 23:12")
    @NotNull(message = "endDateTime can not be null")
    @NotEmpty(message = "endDateTime can not be empty")
    @StockDateTime
    private String endDateTime;

    @ApiModelProperty(notes = "Array of stockPrices should not be empty", name = "stockPrices", required = true)
    @NotNull(message = "stockPrices can not be null")
    @NotEmpty(message = "stockPrices can not be empty")
    private List<Integer> stockPrices;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    public List<Integer> getStockPrices() {
        return stockPrices;
    }

    public void setStockPrices(List<Integer> stockPrices) {
        this.stockPrices = stockPrices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return Objects.equals(id, stock.id) && Objects.equals(startDateTime, stock.startDateTime) && Objects.equals(endDateTime, stock.endDateTime) && Objects.equals(stockPrices, stock.stockPrices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startDateTime, endDateTime, stockPrices);
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", startDateTime='" + startDateTime + '\'' +
                ", endDateTime='" + endDateTime + '\'' +
                ", stockPrices=" + stockPrices +
                '}';
    }
}
