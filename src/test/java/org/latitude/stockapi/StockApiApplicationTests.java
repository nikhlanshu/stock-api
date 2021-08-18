package org.latitude.stockapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.latitude.stockapi.controller.StockController;
import org.latitude.stockapi.dto.Stock;
import org.latitude.stockapi.dto.StockRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = StockApiApplication.class)
class StockApiApplicationTests {

    @Autowired
    private StockController stockController;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(stockController);
    }

    @Test
    void shouldReturnBadRequestWhenStockIsNull() throws Exception {
        this.mockMvc.perform(get("/v1/stock")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(buildRequestBody(null, "2021-08-15 10:01", "2021-08-15 10:01", 1)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", Matchers.is("400 BAD_REQUEST")));

    }

    @Test
    void shouldReturnBadRequestWhenStockIsEmpty() throws Exception {
        this.mockMvc.perform(get("/v1/stock")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(buildRequestBody(List.of(), "2021-08-15 10:01", "2021-08-15 10:01", 1)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", Matchers.is("400 BAD_REQUEST")));

    }

    @Test
    void shouldReturnBadRequestWhenInvalidStartDateTime() throws Exception {
        this.mockMvc.perform(get("/v1/stock")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(buildRequestBody(List.of(40, 20, 50, 10), "2021-08-15 10:0", "2021-08-15 10:01", 1)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", Matchers.is("400 BAD_REQUEST")));

    }

    @Test
    void shouldReturnBadRequestWhenInvalidEndDateTime() throws Exception {
        this.mockMvc.perform(get("/v1/stock")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(buildRequestBody(List.of(40, 20, 50, 10), "2021-08-15 10:01", "2021-08-15 10:12.2", 1)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", Matchers.is("400 BAD_REQUEST")));

    }

    @Test
    void shouldReturnBadRequestWhenInvalidId() throws Exception {
        this.mockMvc.perform(get("/v1/stock")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(buildRequestBody(List.of(40, 20, 50, 10), "2021-08-15 10:01", "2021-08-15 10:12", 0)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", Matchers.is("400 BAD_REQUEST")));

    }

    @Test
    void shouldReturnBadRequestWhenEndDateIsBeforeStartDate() throws Exception {
        this.mockMvc.perform(get("/v1/stock")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(buildRequestBody(List.of(40, 20, 50, 10), "2021-08-16 10:01", "2021-08-15 10:12", 1)))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.errorCode", Matchers.is("500 INTERNAL_SERVER_ERROR")));

    }

    @Test
    void shouldReturnBadRequestWhenIdStartDateEndDateStockPriceAreInvalid() throws Exception {
        this.mockMvc.perform(get("/v1/stock")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(buildRequestBody(null, "2021-08-16 10:01.1", "2021-08-15 10:12.3", -1)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", Matchers.is("400 BAD_REQUEST")));

    }

    @Test
    void shouldReturn200OKWhenValidRequestSent() throws Exception {
        this.mockMvc.perform(get("/v1/stock")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(buildRequestBody(List.of(40, 60, 10, 20, 50), "2021-08-15 10:01", "2021-08-15 14:12", 1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.buyValue", Matchers.is("AUD 10")))
                .andExpect(jsonPath("$.processedDateTime", Matchers.is("2021-08-15T11:01")))
                .andExpect(jsonPath("$.sellValue", Matchers.is("AUD 60")))
                .andExpect(jsonPath("$.maxProfit", Matchers.is("AUD 50")));

    }

    @Test
    void shouldReturn200OKWhenPriceListExceedsTheDateRangeIgnoredAndReturnedTheBestPriceDetails() throws Exception {
        this.mockMvc.perform(get("/v1/stock")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(buildRequestBody(List.of(40, 60, 10, 20, 50, 15), "2021-08-15 10:01", "2021-08-15 13:12", 1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.buyValue", Matchers.is("AUD 10")))
                .andExpect(jsonPath("$.sellValue", Matchers.is("AUD 60")))
                .andExpect(jsonPath("$.processedDateTime", Matchers.is("2021-08-15T11:01")))
                .andExpect(jsonPath("$.maxProfit", Matchers.is("AUD 50")));

    }

    @Test
    void shouldReturn200OKWhenPriceListExceedsAndDuplicatePrices() throws Exception {
        this.mockMvc.perform(get("/v1/stock")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(buildRequestBody(List.of(40, 60, 40, 20, 50, 15), "2021-08-15 10:01", "2021-08-15 13:12", 1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.buyValue", Matchers.is("AUD 15")))
                .andExpect(jsonPath("$.sellValue", Matchers.is("AUD 60")))
                .andExpect(jsonPath("$.processedDateTime", Matchers.is("2021-08-15T11:01")))
                .andExpect(jsonPath("$.maxProfit", Matchers.is("AUD 45")));

    }

    private String buildRequestBody(List<Integer> stocks, String startDate, String endDate, int id) throws JsonProcessingException {
        StockRequest stockRequest = new StockRequest();
        Stock stock = new Stock();
        stock.setId(id);
        stock.setStockPrices(stocks);
        stock.setStartDateTime(startDate);
        stock.setEndDateTime(endDate);
        stockRequest.setStock(stock);
        ObjectWriter objectWriter = new ObjectMapper().writer();
        return objectWriter.writeValueAsString(stockRequest);
    }

}
