# Project Overview

The `saga-choreography` project is a multi-module Spring Boot application designed to implement a saga pattern for managing distributed transactions across multiple services. This project consists of three main services: InventoryService, OrderService, and PaymentService.

## Purpose

The purpose of this project is to demonstrate how to orchestrate complex business processes that span multiple microservices using the saga choreography pattern. Each service is responsible for its own domain and communicates with other services to complete transactions.

## Modules

### InventoryService
- Manages inventory levels and stock availability.
- Provides APIs to check stock and update inventory.

### OrderService
- Handles order creation and management.
- Interacts with InventoryService to ensure stock availability before processing orders.

### PaymentService
- Manages payment processing for orders.
- Communicates with OrderService to confirm order details before processing payments.

## Setup

To set up the project, follow these steps:

1. **Clone the repository:**
   ```
   git clone <repository-url>
   ```

2. **Navigate to the project directory:**
   ```
   cd saga-choreography
   ```

3. **Build the project:**
   ```
   ./gradlew build
   ```

4. **Run the services:**
   Each service can be run independently. Navigate to the respective service directory and use the following command:
   ```
   ./gradlew bootRun
   ```

## Dependencies

This project uses Gradle as the build tool and includes necessary dependencies for Spring Boot and other libraries required for microservices architecture.

## Contributing

Contributions are welcome! Please submit a pull request or open an issue for any enhancements or bug fixes.