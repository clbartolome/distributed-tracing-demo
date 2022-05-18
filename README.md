# distributed-tracing-demo
Repository for demostrating how to enable tracing in a Spring Boot microservice.

- Configuration via Environment Variables:
  - **DB_HOST**: PostgreSQL host url
  - **DB_PORT**: PostgreSQL port
  - **DB_NAME**: PostgreSQL database for catalog
  - **DB_USER**: PostgreSQL user name
  - **DB_PASS**: PostgreSQL user password

## Run locally

```sh
# Start environment (postgres db)
docker-compose up -d

# Once the everything is running, start application using Maven
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-DDB_HOST=localhost -DDB_PORT=5432 -DDB_NAME=foo-db -DDB_USER=develop -DDB_PASS=develop"

# Validate
curl localhost:8080/api/foo | jq
# or
curl localhost:8080/actuator/health
```


