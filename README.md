# CommerceHub Backend API

A scalable and secure backend application for an e-commerce platform built using **Spring Boot**, following clean architecture and industry best practices.

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
- Supports frontend dropdown integration

---

### Pagination & Sorting
- Efficient page-based data retrieval
- Customizable page size and page number
- Sorting by multiple fields (price, name, etc.)
- Supports multi-field sorting

---

### Search & Filtering
- Case-insensitive product search by name
- Filter products by price
- Filter products by category
- Combine multiple filters for advanced queries

---

## Tech Stack

- **Spring Boot**
- **Spring Security**
- **JWT (JSON Web Token)**
- **Spring Data JPA**
- **Hibernate**
- **MySQL**
- **Swagger (OpenAPI)**
- **Spring Boot Actuator**

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


## Pagination Example

```
GET /products?page=0&size=5&sort=price,asc
```


## Authorization

After login, use JWT token in request headers:


Authorization: Bearer 

```

## Swagger UI

Access API documentation:

```
http://localhost:8086/swagger-ui/index.html

```

Use the **Authorize button** to enter JWT token and test secured APIs.



## Actuator Endpoints

```
/actuator/health 
/actuator/info 
/actuator/metrics
```

## Transaction Management

- Uses `@Transactional` for critical operations  
- Ensures data consistency and rollback support  

---

## Exception Handling

- Global exception handling using a centralized handler  
- Structured and consistent API error responses  

---

## Validation

- Request validation using Bean Validation annotations (`@NotNull`, `@NotBlank`, etc.)  

---

## How to Run

1. Clone the repository  
2. Configure database in `application.yml`  
3. Run the Spring Boot application  
4. Access Swagger UI  

---

## Author

**Rajesh Murupoju**