# stock-api

# Read Me First
The following was discovered as part of building this project:

* The original package name 'org.latitude.stock-api' is invalid and this project uses 'org.latitude.stockapi' instead.

# Getting Started
* For API Documentation please visit http://localhost:8443/stock-api/swagger-ui/
* For PostMan request sample visit stock-api/src/test/resource/stock-api.json

# Assumption
* Stock price times sliced exactly one hour starting from the startDateTime. Ex. if startDateTime=2021-08-17 10:10 then the next stock price is logged at 11:10 and so on.
* If the number of prices exceed the range(startDateTime and endDateTime), it is ignored and the calculated stock details returned after buy and sell.
* processingDatetime assumed to be the time at which the stock is sold to get maximum profit.

### Reference Documentation
For further reference, please consider the following sections:
* Refer stock-api/src/test/resource/Latitude_BNPL_coding_challenge.docx

### Docker commands
* docker images
* docker push nikhlanshu/nikhlanshu:stock-api-1.0.0
* docker push stock-api-1.0.0
* docker tag stock-api-1.0.0 nikhlanshu/nikhlanshu:stock-api-1.0.0
* docker images
* docker push nikhlanshu/nikhlanshu:stock-api-1.0.0
