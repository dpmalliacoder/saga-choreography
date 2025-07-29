# OrderService Module Documentation

This module is part of the `saga-choreography` project and is responsible for managing order-related functionalities. 

## Overview

The `OrderService` handles the creation, updating, and retrieval of orders within the system. It interacts with other services to ensure a smooth order processing workflow.

## Setup

To set up the `OrderService`, ensure you have the following prerequisites:

- Java 11 or higher
- Gradle 6.0 or higher

## Running the Service

To run the `OrderService`, navigate to the `OrderService` directory and execute the following command:

```
./gradlew bootRun
```

## Testing

To run the tests for the `OrderService`, use the following command:

```
./gradlew test
```

## API Endpoints

The `OrderService` exposes several RESTful API endpoints for managing orders. Please refer to the API documentation for detailed information on each endpoint.

## Dependencies

This module may have dependencies on other modules within the `saga-choreography` project, such as `InventoryService` and `PaymentService`. Ensure that these services are running when testing the `OrderService`.

## Contribution

If you would like to contribute to the `OrderService`, please fork the repository and submit a pull request with your changes.