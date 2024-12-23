
# Comparison: External Storage vs. Native Persistence

| Feature             | External Storage                         | Native Persistence                      |
|---------------------|------------------------------------------|-----------------------------------------|
| **Primary Use Case** | Integration with legacy systems or existing DBs | Distributed, fault-tolerant database   |
| **Data Durability**  | Managed by the external database        | Managed by Ignite (on-disk storage)    |
| **Performance**      | Slower due to external DB I/O           | High (memory + disk optimization)      |
| **Complexity**       | Requires CacheStore implementation      | Simpler, managed internally by Ignite  |
| **Fault Tolerance**  | Depends on the external database setup  | Built into Ignite's distributed storage |
| **SQL Support**      | Limited (depends on external DB)        | Full SQL support                        |


https://medium.com/@dinesharney/building-a-spring-boot-application-with-apache-ignite-for-caching-0c4c2110ffb8
