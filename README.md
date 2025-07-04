# 🏆 Customer Rewards Points API

This Spring Boot application provides a RESTful API to calculate customer reward points based on their transaction history. It includes dynamic date-based filtering, per-customer breakdown, and monthly reward summaries.

---

## 📐 Problem Statement

For every transaction a customer makes:

- They receive:
    - **2 points** for every dollar spent **over $100**
    - **1 point** for every dollar spent **between $50 and $100**
- No points are awarded for spending **below $50**

### 💡 Example:
A purchase of **$120** will earn:
- `(2 × 20) + (1 × 50) = 90 points`

---

## ⚙️ Tech Stack

| Layer        | Technology            |
|--------------|-----------------------|
| Backend      | Java 17, Spring Boot  |
| Build Tool   | Maven                 |
| API Docs     | Java Docs             |
| Testing      | JUnit 5, Spring Test  |
| JSON Parsing | Jackson (Spring Boot) |

---

## 📁 Project Structure

src
├── main
│   ├── java
│   │   └── com.example.rewards
│   │       ├── controller
│   │       ├── service
│   │       ├── repository
│   │       ├── entity
│   │       ├── dto
│   │       ├── exception
│   │       └── RewardsApplication.java
│   └── resources
│       └── application.properties
├── test
│   └── java
│       └── com.example.rewards
│           ├── controller
│           └── service



---

## 🚀 How to Run
mvn spring-boot:run

### ✅ Prerequisites
- Java 17+
- Maven 3.6+

### 📦 Build the App
```bash
mvn clean install

By default the app runs on: http://localhost:8080

GET /api/rewards
Returns: Monthly and total reward points for all customers.

GET /api/rewards/{customerId}?start=YYYY-MM-DD&end=YYYY-MM-DD
Parameters:

customerId: Customer ID (e.g., 1)

start: Start date (e.g., 2025-04-01)

end: End date (e.g., 2025-06-30)

Returns

{
  "customerId": 1,
  "customerName": "Alice",
  "startDate": "2025-04-01",
  "endDate": "2025-06-30",
  "transactions": [
    { "customerId": 1, "amount": 120.0, "date": "2025-04-01" },
    { "customerId": 1, "amount": 75.0, "date": "2025-04-15" },
    { "customerId": 1, "amount": 200.0, "date": "2025-05-10" }
  ],
  "monthlyPoints": {
    "APRIL 2025": 90,
    "MAY 2025": 250
  },
  "totalPoints": 340
}

