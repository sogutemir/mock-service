# 📋 Mock Microservices API Documentation

## API Overview

This documentation contains technical API details for the Mock Microservices application.

### Base URL
```
http://localhost:8080
```

### Content Type
For all POST requests:
```
Content-Type: application/json
```

## 🔗 API Endpoints Summary

| Service | Method | Endpoint | Description | Status Code |
|---------|--------|----------|-------------|-------------|
| User | GET | `/api/users/{userId}` | Get user information | 200 |
| Order | POST | `/api/orders` | Create new order | 201 |
| Product | GET | `/api/products` | Get product list | 200 |
| Payment | POST | `/api/payments` | Process payment | 200 |
| Inventory | GET | `/api/inventory/{productId}` | Get stock information | 200 |
| Notification | POST | `/api/notifications` | Send notification | 202 |
| Auth | POST | `/api/auth/login` | User login | 200 |
| Shipping | GET | `/api/shipping/{trackingNumber}` | Check shipping status | 200 |
| Catalog | GET | `/api/catalog` | Get category list | 200 |
| Analytics | GET | `/api/analytics` | Get analytics data | 200 |

## 📊 Data Models

### User Model
```json
{
  "id": "integer",
  "name": "string",
  "email": "string (email format)",
  "status": "string (active|inactive|suspended)",
  "createdAt": "string (ISO 8601 datetime)"
}
```

### Order Model
```json
{
  "orderId": "string",
  "userId": "integer",
  "amount": "number (double)",
  "status": "string (pending|confirmed|shipped|delivered|cancelled)",
  "items": [
    {
      "productId": "string",
      "quantity": "integer",
      "price": "number (double)"
    }
  ],
  "createdAt": "string (ISO 8601 datetime)"
}
```

### Product Model
```json
{
  "id": "string",
  "name": "string",
  "price": "number (double)",
  "category": "string",
  "inStock": "boolean"
}
```

### Payment Model
```json
{
  "paymentId": "string",
  "orderId": "string",
  "amount": "number (double)",
  "status": "string (pending|success|failed|refunded)",
  "method": "string (credit_card|debit_card|paypal|bank_transfer)",
  "processedAt": "string (ISO 8601 datetime)"
}
```

### Inventory Model
```json
{
  "productId": "string",
  "quantity": "integer",
  "reserved": "integer",
  "available": "integer",
  "lastUpdated": "string (ISO 8601 datetime)"
}
```

### Notification Model
```json
{
  "notificationId": "string",
  "type": "string (email|sms|push)",
  "recipient": "string",
  "status": "string (queued|sent|delivered|failed)",
  "scheduledAt": "string (ISO 8601 datetime)"
}
```

### Auth Response Model
```json
{
  "token": "string (JWT token)",
  "userId": "integer",
  "expiresIn": "integer (seconds)",
  "tokenType": "string (Bearer)"
}
```

### Shipping Model
```json
{
  "trackingNumber": "string",
  "orderId": "string",
  "carrier": "string",
  "status": "string (pending|picked_up|in_transit|out_for_delivery|delivered|returned)",
  "estimatedDelivery": "string (ISO 8601 datetime)"
}
```

### Category Model
```json
{
  "id": "string",
  "name": "string",
  "productCount": "integer"
}
```

### Analytics Model
```json
{
  "period": "string (daily|weekly|monthly)",
  "date": "string (YYYY-MM-DD)",
  "metrics": {
    "totalOrders": "integer",
    "totalRevenue": "number (double)",
    "uniqueUsers": "integer",
    "averageOrderValue": "number (double)"
  }
}
```

## 🔍 Query Parameters

### Analytics Service
- `period` (optional): `daily`, `weekly`, `monthly`
- `date` (optional): `YYYY-MM-DD` format

### Product Service
- `category` (optional): Filter by category name
- `limit` (optional): Maximum number of products to return

## 📝 Request Examples

### Create Order Request
```json
{
  "userId": 1,
  "amount": 99.99,
  "items": [
    {
      "productId": "PROD-001",
      "quantity": 2,
      "price": 49.99
    }
  ]
}
```

### Payment Request
```json
{
  "orderId": "ORD-12345",
  "amount": 99.99,
  "method": "credit_card"
}
```

### Notification Request
```json
{
  "type": "email",
  "recipient": "john.doe@example.com",
  "message": "Your order has been confirmed"
}
```

### Login Request
```json
{
  "username": "john.doe",
  "password": "secret123"
}
```

## ⚠️ Error Responses

Standard HTTP status codes are used for all error situations:

- `400 Bad Request`: Invalid request format
- `404 Not Found`: Resource not found
- `500 Internal Server Error`: Server error

## 🔧 WireMock Configuration

### Mapping Files Location
```
src/main/resources/wiremock/mappings/
```

### Response Files Location
```
src/main/resources/wiremock/__files/
```

### URL Patterns
- Exact match: `"url": "/api/orders"`
- Pattern match: `"urlPattern": "/api/users/.*"`
- Path parameters: `{userId}`, `{productId}`, `{trackingNumber}`

## 🧪 Testing

### Health Check
```bash
curl http://localhost:8081/docs/health
```

### API Documentation
```bash
curl http://localhost:8081/docs/openapi.yaml
```

### Sample Test Commands
```bash
# Test all services
for endpoint in users/1 products inventory/PROD-001 catalog analytics shipping/TRK-789123; do
  echo "Testing GET /api/$endpoint"
  curl -s "http://localhost:8080/api/$endpoint" | jq .
done

# Test POST endpoints
curl -X POST http://localhost:8080/api/orders -H "Content-Type: application/json" -d '{"userId":1,"amount":99.99}'
curl -X POST http://localhost:8080/api/payments -H "Content-Type: application/json" -d '{"orderId":"ORD-12345","amount":99.99,"method":"credit_card"}'
curl -X POST http://localhost:8080/api/notifications -H "Content-Type: application/json" -d '{"type":"email","recipient":"test@example.com","message":"Test"}'
curl -X POST http://localhost:8080/api/auth/login -H "Content-Type: application/json" -d '{"username":"john","password":"secret"}'
```

## 📊 Performance Notes

- WireMock responses are served instantly (no artificial delays)
- All data is static and predefined
- No database connections or external API calls
- Suitable for high-volume testing scenarios

## 🔒 Security Notes

- No authentication required for mock endpoints
- All endpoints return predefined responses
- Not suitable for production use
- Contains sample/dummy data only

## 📈 Monitoring

### Available Metrics
- Application health status
- WireMock server status
- Port information
- Documentation availability

### Logging
- WireMock request/response logging available
- Configurable log levels in `application.yml`
- Debug mode available for troubleshooting

---

**Last Updated**: 2025-07-18  
**API Version**: 1.0.0  
**WireMock Version**: 2.35.0