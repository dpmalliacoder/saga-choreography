# InventoryService Module

This module is part of the `saga-choreography` project, which implements a saga pattern for managing distributed transactions across multiple services.

## Purpose

The `InventoryService` is responsible for managing inventory levels, handling stock availability, and processing inventory-related requests from other services.

## Setup

To set up the `InventoryService`, ensure you have the following prerequisites:

- Java 11 or higher
- Gradle 6.0 or higher

### Running the Service

1. Navigate to the `InventoryService` directory.
2. Use the following command to build and run the service:

   ```
   ./gradlew bootRun
   ```

### Testing

To run the tests for the `InventoryService`, execute:

```
./gradlew test
```

## Dependencies

This module may have dependencies that are defined in the `build.gradle` file located in the `InventoryService` directory. Make sure to review and update them as necessary.

## Contributing

If you wish to contribute to the `InventoryService`, please fork the repository and submit a pull request with your changes. Ensure that your code adheres to the project's coding standards and includes appropriate tests.