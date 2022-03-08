## LowLatencyMicroserviceApplication

### This application explains the basic usage of Off-Heap Storage/Kernel bypass using Chronicle Map and Queue

#### URLs to access rest endpoints

* hello: http://localhost:8080/v1/book
* GetPrices: http://localhost:8080/v1/prices
* Save: http://localhost:8080/v1/save
    * RequestBody:
      ```json
        {
        "bid": "20.00",
        "ask": "22.00",
        "spot": "20.00",
        "currencyPair": "USD/JPY"
        }
      ```

### Spring Doc (Swagger)

* http://localhost:8080/swagger-ui.html