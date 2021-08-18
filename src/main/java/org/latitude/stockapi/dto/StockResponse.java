package org.latitude.stockapi.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.latitude.stockapi.annotation.StockDateTime;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@ApiModel(description = "Stock Request model")
@JsonPropertyOrder({"stock", "processedDateTime","maxProfit", "buyValue", "sellValue"})
public class StockResponse {

    @ApiModelProperty(notes = "Non-null stock", name = "stock", required = true)
    @Valid
    @NotNull(message = "stock can not be null")
    private Stock stock;

    @ApiModelProperty(notes = "processing date time refers to when the stock is sold in yyyy-MM-dd HH:mm format", name = "processedDateTime", required = true, value = "2018-12-10 23:12")
    @NotNull(message = "processedDateTime can not be null")
    @NotEmpty(message = "processedDateTime can not be empty")
    @StockDateTime
    private String processedDateTime;

    @ApiModelProperty(notes = "Difference between the sold price and bought price", name = "maxProfit", required = true, value = "5 AUD")
    @NotNull(message = "maxProfit can not be null")
    @NotEmpty(message = "maxProfit can not be empty")
    private String maxProfit;

    @ApiModelProperty(notes = "stock buy Value", name = "buyValue", required = true, value = "5 AUD")
    @NotNull(message = "buyValue can not be null")
    @NotEmpty(message = "buyValue can not be empty")
    private String buyValue;

    @ApiModelProperty(notes = "stock sell value", name = "sellValue", required = true, value = "5 AUD")
    @NotNull(message = "sellValue can not be null")
    @NotEmpty(message = "sellValue can not be empty")
    private String sellValue;

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public String getMaxProfit() {
        return maxProfit;
    }

    public void setMaxProfit(String maxProfit) {
        this.maxProfit = maxProfit;
    }

    public String getBuyValue() {
        return buyValue;
    }

    public void setBuyValue(String buyValue) {
        this.buyValue = buyValue;
    }

    public String getSellValue() {
        return sellValue;
    }

    public void setSellValue(String sellValue) {
        this.sellValue = sellValue;
    }

    public String getProcessedDateTime() {
        return processedDateTime;
    }

    public void setProcessedDateTime(String processedDateTime) {
        this.processedDateTime = processedDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockResponse that = (StockResponse) o;
        return Objects.equals(stock, that.stock) && Objects.equals(processedDateTime, that.processedDateTime) && Objects.equals(maxProfit, that.maxProfit) && Objects.equals(buyValue, that.buyValue) && Objects.equals(sellValue, that.sellValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stock, processedDateTime, maxProfit, buyValue, sellValue);
    }

    @Override
    public String toString() {
        return "StockResponse{" +
                "stock=" + stock +
                ", processedDateTime='" + processedDateTime + '\'' +
                ", maxProfit='" + maxProfit + '\'' +
                ", buyValue='" + buyValue + '\'' +
                ", sellValue='" + sellValue + '\'' +
                '}';
    }
}
