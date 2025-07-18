# 🚀 Mock Microservices API

Comprehensive microservice mock application created with WireMock. This project simulates 10 different microservices and can be used for testing environments.

## 📋 Contents

- [Features](#features)
- [Installation](#installation)
- [Running](#running)
- [API Documentation](#api-documentation)
- [Services](#services)
- [Test Examples](#test-examples)
- [Configuration](#configuration)
- [Troubleshooting](#troubleshooting)

## ✨ Features

- **10 Microservice Mocks**: User, Order, Product, Payment, Inventory, Notification, Auth, Shipping, Catalog, Analytics
- **OpenAPI 3.0.3 Specification**: Complete API documentation
- **Web-Based Documentation**: Browser-accessible API guide
- **Realistic Data**: Meaningful mock data for each service
- **Easy Configuration**: YAML-based settings
- **Health Check**: System status monitoring
- **Docker Support**: Containerizable structure

## 🛠 Installation

### Requirements

- Java 11 or higher
- Maven 3.6 or higher
- Git

### Cloning the Project

```bash
git clone <repository-url>
cd mock-service
```

### Installing Dependencies

```bash
mvn clean install
```

## 🚀 Running

### Running with Maven

```bash
mvn spring-boot:run
```

### Running with JAR File

```bash
# First build the project
mvn clean package

# Run the JAR file
java -jar target/mock-microservices-1.0.0.jar
```

### After Application Starts

- **WireMock Server**: http://localhost:8080
- **Spring Boot App**: http://localhost:8081
- **API Documentation**: http://localhost:8081/docs/

## 📚 API Documentation

### Documentation Access

- **Web Interface**: [http://localhost:8081/docs/](http://localhost:8081/docs/)
- **OpenAPI YAML**: [http://localhost:8081/docs/openapi.yaml](http://localhost:8081/docs/openapi.yaml)
- **Health Check**: [http://localhost:8081/docs/health](http://localhost:8081/docs/health)

### Swagger UI Alternative

To view the OpenAPI specification in Swagger Editor:

1. Go to [https://editor.swagger.io/](https://editor.swagger.io/)
2. Select `File > Import URL`
3. Enter the URL `http://localhost:8081/docs/openapi.yaml`

## 🔧 Services

### 1. 👤 User Service

**Endpoint**: `GET /api/users/{userId}`

**Description**: Gets user information

**Example Request**:
```bash
curl -X GET http://localhost:8080/api/users/1
```

**Example Response**:
```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john.doe@example.com",
  "status": "active",
  "createdAt": "2023-01-01T00:00:00Z"
}
```

### 2. 📦 Order Service

**Endpoint**: `POST /api/orders`

**Description**: Creates a new order

**Example Request**:
```bash
curl -X POST http://localhost:8080/api/orders \
  -H "Content-Type: application/json" \
  -d '{
    "userId": 1,
    "amount": 99.99,
    "items": [
      {
        "productId": "PROD-001",
        "quantity": 2,
        "price": 49.99
      }
    ]
  }'
```

**Example Response**:
```json
{
  "orderId": "ORD-12345",
  "userId": 1,
  "amount": 99.99,
  "status": "confirmed",
  "items": [
    {
      "productId": "PROD-001",
      "quantity": 2,
      "price": 49.99
    }
  ],
  "createdAt": "2023-01-01T10:00:00Z"
}
```

### 3. 🛍 Product Service

**Endpoint**: `GET /api/products`

**Description**: Gets the product list

**Example Request**:
```bash
curl -X GET http://localhost:8080/api/products
```

**Example Response**:
```json
{
  "products": [
    {
      "id": "PROD-001",
      "name": "Wireless Headphones",
      "price": 49.99,
      "category": "Electronics",
      "inStock": true
    },
    {
      "id": "PROD-002",
      "name": "Smart Watch",
      "price": 199.99,
      "category": "Electronics",
      "inStock": false
    }
  ]
}
```

### 4. 💳 Payment Service

**Endpoint**: `POST /api/payments`

**Description**: Processes payment

**Example Request**:
```bash
curl -X POST http://localhost:8080/api/payments \
  -H "Content-Type: application/json" \
  -d '{
    "orderId": "ORD-12345",
    "amount": 99.99,
    "method": "credit_card"
  }'
```

**Example Response**:
```json
{
  "paymentId": "PAY-67890",
  "orderId": "ORD-12345",
  "amount": 99.99,
  "status": "success",
  "method": "credit_card",
  "processedAt": "2023-01-01T10:05:00Z"
}
```

### 5. 📊 Inventory Service

**Endpoint**: `GET /api/inventory/{productId}`

**Description**: Gets product stock information

**Example Request**:
```bash
curl -X GET http://localhost:8080/api/inventory/PROD-001
```

**Example Response**:
```json
{
  "productId": "PROD-001",
  "quantity": 150,
  "reserved": 10,
  "available": 140,
  "lastUpdated": "2023-01-01T09:00:00Z"
}
```

### 6. 🔔 Notification Service

**Endpoint**: `POST /api/notifications`

**Description**: Sends notification

**Example Request**:
```bash
curl -X POST http://localhost:8080/api/notifications \
  -H "Content-Type: application/json" \
  -d '{
    "type": "email",
    "recipient": "john.doe@example.com",
    "message": "Your order has been confirmed"
  }'
```

**Example Response**:
```json
{
  "notificationId": "NOT-555",
  "type": "email",
  "recipient": "john.doe@example.com",
  "status": "queued",
  "scheduledAt": "2023-01-01T10:10:00Z"
}
```

### 7. 🔐 Auth Service

**Endpoint**: `POST /api/auth/login`

**Description**: Authenticates user

**Example Request**:
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "john.doe",
    "password": "secret123"
  }'
```

**Example Response**:
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "userId": 1,
  "expiresIn": 3600,
  "tokenType": "Bearer"
}
```

### 8. 🚚 Shipping Service

**Endpoint**: `GET /api/shipping/{trackingNumber}`

**Description**: Checks shipping status

**Example Request**:
```bash
curl -X GET http://localhost:8080/api/shipping/TRK-789123
```

**Example Response**:
```json
{
  "trackingNumber": "TRK-789123",
  "orderId": "ORD-12345",
  "carrier": "FastShip",
  "status": "in_transit",
  "estimatedDelivery": "2023-01-03T15:00:00Z"
}
```

### 9. 📂 Catalog Service

**Endpoint**: `GET /api/catalog`

**Description**: Gets category list

**Example Request**:
```bash
curl -X GET http://localhost:8080/api/catalog
```

**Example Response**:
```json
{
  "categories": [
    {
      "id": "CAT-001",
      "name": "Electronics",
      "productCount": 150
    },
    {
      "id": "CAT-002",
      "name": "Clothing",
      "productCount": 200
    }
  ]
}
```

### 10. 📈 Analytics Service

**Endpoint**: `GET /api/analytics`

**Description**: Gets analytics data

**Example Request**:
```bash
curl -X GET http://localhost:8080/api/analytics?period=daily&date=2023-01-01
```

**Example Response**:
```json
{
  "period": "daily",
  "date": "2023-01-01",
  "metrics": {
    "totalOrders": 45,
    "totalRevenue": 2250.75,
    "uniqueUsers": 38,
    "averageOrderValue": 50.02
  }
}
```

## 🧪 Test Examples

### Postman Collection

You can use the Postman collection to test all API endpoints:

```bash
# To import the collection
# 1. Open Postman
# 2. Select Import > Link
# 3. Enter the URL http://localhost:8081/docs/openapi.yaml
```

### Automated Test Script

```bash
#!/bin/bash

BASE_URL="http://localhost:8080"

echo "🧪 Mock Microservices API Test Script"
echo "======================================"

# User Service Test
echo "1. Testing User Service..."
curl -s -X GET $BASE_URL/api/users/1 | jq .

# Order Service Test
echo "2. Testing Order Service..."
curl -s -X POST $BASE_URL/api/orders \
  -H "Content-Type: application/json" \
  -d '{"userId": 1, "amount": 99.99}' | jq .

# Product Service Test
echo "3. Testing Product Service..."
curl -s -X GET $BASE_URL/api/products | jq .

# Payment Service Test
echo "4. Testing Payment Service..."
curl -s -X POST $BASE_URL/api/payments \
  -H "Content-Type: application/json" \
  -d '{"orderId": "ORD-12345", "amount": 99.99, "method": "credit_card"}' | jq .

# Inventory Service Test
echo "5. Testing Inventory Service..."
curl -s -X GET $BASE_URL/api/inventory/PROD-001 | jq .

echo "✅ Test completed!"
```

## ⚙️ Configuration

### application.yml

```yaml
server:
  port: 8081

spring:
  application:
    name: mock-microservices

logging:
  level:
    com.github.tomakehurst.wiremock: DEBUG
    root: INFO
```

### Changing Ports

To change the WireMock port in `MockServiceApplication.java` file:

```java
.port(8080) // Change this value
```

To change the Spring Boot port in `application.yml` file:

```yaml
server:
  port: 8081 # Change this value
```

## 🐳 Docker Support

### Dockerfile

```dockerfile
FROM openjdk:11-jre-slim

COPY target/mock-microservices-1.0.0.jar app.jar

EXPOSE 8080 8081

ENTRYPOINT ["java", "-jar", "/app.jar"]
```

### Running with Docker

```bash
# Build the image
docker build -t mock-microservices .

# Run the container
docker run -p 8080:8080 -p 8081:8081 mock-microservices
```

## 🔧 Troubleshooting

### Common Errors and Solutions

#### 1. Port Conflict
**Error**: `Port 8080 is already in use`

**Solution**: 
- Another application might be using the port
- Change the port or close the conflicting application

#### 2. Java Version Error
**Error**: `Unsupported class file major version`

**Solution**: 
- Make sure you're using Java 11 or higher
- Check with `java -version`

#### 3. Maven Build Error
**Error**: `Failed to execute goal`

**Solution**: 
```bash
mvn clean install -U
```

#### 4. WireMock Files Not Found
**Error**: `Mapping file not found`

**Solution**: 
- Check the folder structure at `src/main/resources/wiremock/`
- Make sure the file names are correct

### Increasing Log Level

For more detailed logs in `application.yml` file:

```yaml
logging:
  level:
    com.github.tomakehurst.wiremock: DEBUG
    com.github.tomakehurst.wiremock.http: DEBUG
    root: DEBUG
```

### Health Check

To check the application status:

```bash
curl http://localhost:8081/docs/health
```

## 📄 License

This project is licensed under the MIT License. See the `LICENSE` file for details.


---

**Note**: This project is a mock service created for testing purposes. Before using it in a production environment, evaluate security and performance.
