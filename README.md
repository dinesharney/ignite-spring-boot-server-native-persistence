# ignite-spring-boot-server-native-persistence

A Spring Boot application demonstrating **Apache Ignite** with **native persistence** enabled. Unlike traditional setups that depend on external databases, this project uses Ignite's built-in storage to provide a fully in-memory + durable **distributed cache and data store**.

---

## Key Features

- Apache Ignite native persistence (no external DB needed)
- Data survives restarts (durable storage)
- Distributed in-memory + on-disk cache
- SQL and key-value APIs supported
- Spring Boot integration with embedded Ignite node
- Simple REST APIs for CRUD operations

---

## System Architecture

- **Client API Call**
  - Sends REST request to Spring Boot app
- **Spring Boot Application (Ignite Node)**
  - Hosts Ignite as embedded instance
  - Executes cache operations and SQL queries
- **Apache Ignite Cache with Native Persistence**
  - Stores data in memory and persists on disk
  - Survives node restarts and cluster crashes

---

## Tech Stack

- Java 17
- Spring Boot 3.x
- Apache Ignite 2.15+ (with persistence)
- Maven

---

## Project Structure

- `controller/` – REST APIs (`CustomerController.java`)
- `model/` – Ignite-friendly POJO (`Customer.java`)
- `config/` – Ignite native persistence configuration (`IgniteConfig.java`)
- `IgniteSpringBootApp.java` – Main Spring Boot entry point

---

## Getting Started

1. **Clone the Repository**

   ```bash
   git clone ttps://github.com/dinesharney/ignite-spring-boot-server-native-persistence
   cd ignite-spring-boot-server-native-persistence


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
