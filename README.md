## Backend technical interview for atsistemas in A Coruña

* Build a simple SpringBoot application/service that provides a REST endpoint for querying that:
  * Accepts as input params:
    * Product ID
    * Brand ID
    * Application date
  * Returns as output:
    * Produt ID
    * Brand ID
    * Application dates (Start and End)
    * Rate to apply to the price
    * Final price

* An in-memory DB (h2) should be used and initialized with the example data.


## How to run it?

```
mvn clean package
```
```
docker build . -t atsistemas
```
```
docker run --name atsistemas -d -p 8080:8080 -t atsistemas
```

## How can I test it?

Using curl:

```
curl --location 'http://localhost:8080/price?productId=35455&brandId=1&date=2020-06-14-10.00.00'
```


