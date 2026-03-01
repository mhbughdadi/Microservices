# Copilot / AI Agent Instructions for products-ms 🧭

## Quick summary
- This repo hosts a Spring Boot **Products microservice** (Java 21, Maven 3.8+, Lombok, Spring Data JPA).
- Key runtime ports: **9090** (default), dev profile uses **9091** (`application-dev.properties`).
- Uses **MySQL**, **Logstash/ELK**, **SonarQube** and can be run via `docker-compose.yml` at the repo root.

---

## Architecture & where to look 🔎
- Entry point: `src/main/java/com/apogee/product/ProductApplication.java`.
- Typical package layout (important):
  - `controllers/` → HTTP endpoints (e.g., `ProductController.java`)
  - `backingService/` → orchestration layer used by controllers (e.g., `ProductsBackingService`)
  - `services/` → domain services, `repositories/` → JPA repositories
  - `utilities/` → custom mapper and helpers (`Mapper.java`, `Utilities.java`)
  - `advices/` → `GlobalExceptionHandler.java` (localization via `errors_*.properties`)
  - `aop/` → `LoggerAspect.java` (request/response logging, adds `requestId`)

> Pattern: Controllers delegate to a *backing service* rather than directly to repositories. Use backing service tests as examples for orchestration logic.

---

## Important project conventions & patterns ✅
- Mapping: custom mapper lives at `src/main/java/com/apogee/product/utilities/Mapper.java`. Prefer using it over ad-hoc conversions.
- I18N: messages and errors are resolved via resource bundles `errors_en.properties` / `errors_ar.properties` and `messages_*.properties`.
- Exception handling: API errors return `FailureResponse` via `GlobalExceptionHandler` (uses bundle keys as exception messages).
- Logging: `LoggerAspect` logs request/response bodies, headers, path variables and sets `requestId`. Log4j2 is configured with a Socket appender to `logstash:5044` (`log4j2.xml`).
- DB config defaults: `spring.datasource.url=jdbc:mysql://localhost:3306/products_ms_db` with user `root` and password `rootpassword` (also defined in `docker-compose.yml`).
- Docker: `products-ms/DockerFile` is a multi-stage build; the entrypoint expects env vars `SPRING_DATASOURCE_*`.

---

## How to build & run 🛠️
- Local (dev):
  - Build: `mvn clean install`
  - Run: `mvn spring-boot:run` (or run the generated jar in `target/`)
  - Run with dev profile (uses `application-dev.properties`): `mvn spring-boot:run -Dspring-boot.run.profiles=dev` or pass `--spring.profiles.active=dev`.
- Docker-compose (recommended to bring DB, ELK & Sonar):
  - Up (rebuild images): `docker-compose up --build`
  - Bring a single service for debugging: `docker-compose up --build products-ms`
- Tests: `mvn test` (reports in `target/surefire-reports`)
- Sonar scan: see `pom.xml` properties. Example:
  ```bash
  mvn clean verify sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.login=$SONAR_TOKEN
  ```

---

## Useful endpoints & examples ✳️
- Swagger UI: `http://localhost:9090/swagger-ui.html` (OpenAPI via `springdoc` dependency).
- Example endpoints (see `ProductController`):
  - GET  `/products` → `ProductController.allProducts()`
  - GET  `/products/{productId}` → `ProductController.findProduct(...)`
  - POST `/products` → `ProductController.addProduct(...)` (request body: `ProductDto`)
  - DELETE `/products/{productId}` → `ProductController.deleteProduct(...)`

---

## Integration points & external dependencies 🔗
- MySQL (container `mysql-db` in `docker-compose.yml`) — DB name `products_ms_db`.
- ELK stack: `logstash`, `elasticsearch`, `kibana` (docker-compose includes config). Logs are sent via socket to `logstash:5044`.
- SonarQube: `sonarqube` + `sonar-db` services in compose; Maven plugin configured in `pom.xml`.

---

## Quick developer tips & gotchas ⚠️
- If you need logs in Kibana, ensure Logstash and Elasticsearch are running before `products-ms` (compose already sets `depends_on`).
- The aspect logs request bodies and headers — avoid adding secrets directly to requests or mask sensitive fields when adding new logs.
- The Dockerfile passes DB config with env vars `SPRING_DATASOURCE_*`; tests and local runs use `application.properties` defaults.
- Repository scanning is enabled explicitly with `@EnableJpaRepositories(basePackages = "com.apogee.product.repositories")` in `ProductApplication.java`.

---

If anything is unclear or you want more examples (e.g., common test fixtures, how backing services orchestrate domain services), tell me which section to expand and I will iterate. ✅
