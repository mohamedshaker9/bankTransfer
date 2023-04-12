# Simple Demo Of Monolithic  Architecture

This is a Spring REST API project developed using Java and PostgreSQL for demonstrating the monolithic architecture.

## Features

Transfer Api to transfer amount from an account to another account

## Technology Stack

- Java 17
- Spring Boot 3.0.5
- PostgreSQL
- Maven

## Getting Started

To get started with this project, follow these steps:

- Clone this repository to your local machine.
- Install PostgreSQL and create a database called banking.
- Create these two tables in banking with the following schemas:
```postgresql
  CREATE TABLE accounts (
      account_number VARCHAR(10) PRIMARY KEY,
      balance DECIMAL(10, 2)
      );
  INSERT INTO accounts (account_number, balance)
  VALUES ('123456789', 1000.00),
         ('987654321', 500.00);
```
- Update the database configuration in [Application properties](src/main/resources/application.yml) to match your PostgreSQL database configuration.
- Build and run the project using Maven: 
```shell 
  ./mvn spring-boot:run
```

## API Endpoint

The following API endpoint are available:

## Transfer

POST /api/transfer

Sample Request Body

```json
{
    "fromAccount": "B1",
    "toAccount": "A1",
    "amount": 100000
}
```
