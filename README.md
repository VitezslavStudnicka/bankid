You can test endpoints in exchange-rates-api.http

or Alternatively:

Fetch currency list for CZK

curl -X GET 'http://localhost:8080/api/v1/exchange-rates/pairs' \
-H 'Authorization: Basic dXNlcjpwYXNzd29yZA=='

Compare CZK to XXX Currency

curl -X GET 'http://localhost:8080/api/v1/exchange-rates/compare?base=USD' \
-H 'Authorization: Basic dXNlcjpwYXNzd29yZA=='

or in browser and credentials user:password



- I used simple controller for API for simplicity.
Otherwise prefer Open Api generator with OAS schema in .yaml better solution to keep API documentation in order

- Domain Driven Structure
- Test only for example at Exchange Service