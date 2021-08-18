package org.latitude.stockapi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.latitude.stockapi.annotation.LogRequestResponse;
import org.latitude.stockapi.dto.StockRequest;
import org.latitude.stockapi.dto.StockResponse;
import org.latitude.stockapi.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = "StockController", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@RestController
@RequestMapping(path = "/v1/stock", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class StockController {
    private final StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @ApiOperation(value = "Gets Stock details for a period of time", tags = "getStockDetails")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal or Unknown Error")})
    @GetMapping
    @LogRequestResponse
    public @Valid ResponseEntity<StockResponse> getStockDetails(@RequestBody @Valid StockRequest stockRequest) {
        StockResponse stockResponse = stockService.processStock(stockRequest);
        return new ResponseEntity<>(stockResponse, HttpStatus.OK);
    }
}
