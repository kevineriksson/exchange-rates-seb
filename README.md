# Exchange rate application

Overview
This project consists of a backend service and a frontend application that fetches, stores, and displays exchange rate data from an API. The backend is built using Spring Boot and the frontend uses Angular.

The project had these requirements:
Write a web application with such functionality:
1. Central bank exchange rates calculation API. Exchange rates from the European Central Bank. https://www.ecb.europa.eu/stats/policy_and_exchange_rates/euro_reference_exchange_rates/html/index.en.html
2. Currency calculation API. The amount is entered, the currency as an input, amount in foreign currency and the rate at which it was calculated as an output.
3. API to get TOP 5 currencies with the highest growth 10 days.
4. API to get TOP 5 currencies with the highest growth and decline over a 10-day period in the last 90 days.
5. Exchange rates must be automatically obtained every day (e.g. using quartz).
6. Use the H2 database for data storage.
7. Initially, if no rates yet loaded - populate rates for last 90 days.

# Backend - Spring Boot
Requirements:
1. Java 17 or higher
2. Spring Boot 2.x
3. H2 Database
4. Maven or Gradle 

Steps:
1. Clone the repository
2. Navigate to exchange-rates-api
3. Run the project

Endpoints
GET /api/currencyRates: Fetch the latest exchange rate data from the database for today.
GET /api/currencyRates/growth: Retrieve the top 5 currency movers based on biggest growth.
GET /api/currencyRates/movement: Retrieve the top 5 currency movers based on biggest movement in last 90 days over 10 day period.

# Frontend - Angular
1. Requirements:
2. Node.js
3. Angular CLI
4. NPM

Steps:
1. Clone the repository:
2. Navigate to the exchange-rates-app
3. Install dependencies
4. Run the application locally: ng serve
The frontend will be accessible at http://localhost:4200.
