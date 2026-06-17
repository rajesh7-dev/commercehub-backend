# CommerceHub Backend API

A scalable, secure, and production-ready backend application for an e-commerce platform built using **Spring Boot**, following clean architecture and industry best practices.

---

## System Overview

CommerceHub is a backend system designed to support core e-commerce operations such as user authentication, product management, cart handling, and order processing.

The system follows a layered architecture with clear separation of concerns:
- Controllers handle HTTP requests
- Services manage business logic
- Repositories interact with the database
- Entities represent domain models

It ensures secure access using JWT authentication and provides scalable REST APIs for frontend integration.

---

## Architecture

The application follows a **layered architecture (Monolithic)**:

```
Client → Controller → Service → Repository → Database
```

### Layers:
- **Controller Layer** – Handles incoming HTTP requests and responses  
- **Service Layer** – Contains business logic and validations  
- **Repository Layer** – Handles database interactions using Spring Data JPA  
- **Entity Layer** – Defines database models  

---

### Key Principles:
- Separation of concerns  
- Clean API design  
- Scalable structure  

---

## Highlights

- End-to-end e-commerce backend implementation  
- Secure authentication using JWT  
- Clean architecture with proper layering  
- DTO usage for safe API responses  
- Cart → Order → Payment flow implemented  
- SLF4J logging and Actuator monitoring  
- Ready for microservices migration 

---

## Key Features

### Authentication & Security
- JWT-based authentication
- Role-Based Access Control (USER, SELLER, ADMIN)
- Spring Security with custom filters
- Method-level authorization using `@PreAuthorize`

---

### Product Management
- Create, update, delete, and fetch products
- DTO-based API design (clean and secure responses)
- Seller-specific product management

---

### Category Management
- Create and manage categories
- Assign products to categories
- Fetch products by category

---

### Search & Filtering
- Search products by name (case-insensitive)
- Filter by price range
- Filter by category
- Combined filtering support

---

### Pagination & Sorting
- Efficient page-based data retrieval
- Dynamic page size
- Sorting by fields (price, name)
- Supports multi-field sorting

---

### Cart Module
- Add products to cart
- Update item quantity
- Remove items from cart
- View cart details
- Total price calculation

---

### Order Module
- Place order (checkout flow)
- Convert cart → order
- Store order items
- Order history for users

---

### Payment Module (Mock)
- Simulated payment processing
- Integrated in checkout flow
- Returns success for demonstration

---

### Monitoring & Logging
- Spring Boot Actuator endpoints
- SLF4J logging integration
- Health, metrics, and info endpoints

---


## Tech Stack

- **Java 17**
- **Spring Boot**
- **Spring Security**
- **JWT (JSON Web Token)**
- **Spring Data JPA**
- **Hibernate**
- **MySQL**
- **Swagger (OpenAPI)**
- **Spring Boot Actuator**
- **SLF4J Logging**

---

## API Endpoints

### Authentication APIs

```
POST /api/auth/register   
POST /api/auth/login
```


### Product APIs

```
POST /products   
GET /products    
GET /products/search    
GET /products/filter   
GET /products/by-category
```

### Category APIs

```
POST /categories  
GET /categories
```

### Cart APIs

```
POST   /cart/add
PUT    /cart/update
DELETE /cart/remove
GET    /cart
```

### Order APIs

```
POST /orders/checkout
GET  /orders
```

## Pagination Example

```
GET /products?page=0&size=5&sort=price,asc
```


## Authorization

After login, use JWT token in request headers:

```
Authorization: Bearer 
```

## Swagger UI

```
http://localhost:8086/swagger-ui/index.html

```



## Actuator Endpoints

```
/actuator/health 
/actuator/info 
/actuator/metrics
```

---

## Transaction Management

- Uses `@Transactional` for critical operations  
- Ensures data consistency and rollback support  

---

## Exception Handling

- Global exception handler implemented  
- Consistent API error responses  

---

## Validation

- Request validation using Bean Validation  
- (`@NotNull`, `@NotBlank`, etc.) 

---

## How to Run

1. Clone the repository  
2. Configure database in `application.yml`  
3. Run the Spring Boot application  
4. Access Swagger UI  

---

## Future Scope

- Real payment gateway integration (Stripe / Razorpay)
- Order status tracking (PLACED, SHIPPED, DELIVERED)
- Inventory management
- Email notifications
- Caching (Redis)
- Microservices architecture migration
- API Gateway & Service Discovery

---

## Author

**Rajesh Murupoju**