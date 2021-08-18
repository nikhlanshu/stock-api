package org.latitude.stockapi.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@ApiModel(description = "Stock Request model")
@JsonPropertyOrder({"stock"})
public class StockRequest {

    @ApiModelProperty(notes = "Non-null stock", name = "stock", required = true)
    @Valid
    @NotNull(message = "stock can not be null")
    private Stock stock;

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockRequest that = (StockRequest) o;
        return Objects.equals(stock, that.stock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stock);
    }

    @Override
    public String toString() {
        return "StockRequest{" +
                "stock=" + stock +
                '}';
    }
}
