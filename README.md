# ignite-spring-boot-server-external-storage

A sample Spring Boot application demonstrating **Apache Ignite** as a distributed **read-through / write-through cache** backed by an **external persistent store** (like a relational database). This setup provides **low-latency access** to data while maintaining consistency with the underlying data store.

---

## Key Features

- Apache Ignite embedded in Spring Boot
- Read-through: Auto-load from DB on cache miss
- Write-through: Auto-persist to DB on cache update
- Pluggable `CacheStore` implementation
- Simple REST APIs for CRUD operations
- In-memory H2 used as demo database (replaceable with MySQL/Postgres)

---

## System Architecture

- **Client API Call**
  - Sends request to Spring Boot app
- **Spring Boot Application (embedded Ignite node)**
  - Hosts REST APIs
  - Connects to Ignite Cache
- **Apache Ignite Cache**
  - Acts as read-through/write-through cache
  - Delegates load/write to external data store
- **External Persistent Store**
  - Backed by H2 database (or any JDBC-compatible DB)

---

## Tech Stack

- Java 17
- Spring Boot 3.x
- Apache Ignite 2.15+
- Spring Data JPA
- H2 In-Memory Database
- Maven

---

## Project Structure

- `controller/` – REST APIs (`CustomerController.java`)
- `model/` – JPA Entity (`Customer.java`)
- `repository/` – Spring Data interface (`CustomerRepository.java`)
- `store/` – Ignite `CacheStore` implementation (`CustomerCacheStore.java`)
- `config/` – Ignite and cache configuration (`IgniteConfig.java`)
- `IgniteSpringBootApp.java` – Main Spring Boot application entry point

---

## Getting Started

1. **Clone the Repository**

   ```bash
   git clone https://github.com/your-org/ignite-spring-boot-server-external-storage.git
   cd ignite-spring-boot-server-external-storage

### 2. Build the App
./mvnw clean install

### 2. Run the App
./mvnw spring-boot:run



# Comparison: External Storage vs. Native Persistence

| Feature             | External Storage                         | Native Persistence                      |
|---------------------|------------------------------------------|-----------------------------------------|
| **Primary Use Case** | Integration with legacy systems or existing DBs | Distributed, fault-tolerant database   |
| **Data Durability**  | Managed by the external database        | Managed by Ignite (on-disk storage)    |
| **Performance**      | Slower due to external DB I/O           | High (memory + disk optimization)      |
| **Complexity**       | Requires CacheStore implementation      | Simpler, managed internally by Ignite  |
| **Fault Tolerance**  | Depends on the external database setup  | Built into Ignite's distributed storage |
| **SQL Support**      | Limited (depends on external DB)        | Full SQL support                        |

## Further Reading
https://medium.com/@dinesharney/building-a-spring-boot-application-with-apache-ignite-for-caching-0c4c2110ffb8
