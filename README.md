# distributed-tracing-demo
Repository for demostrating how to enable tracing in a Spring Boot microservice.

- Configuration via Environment Variables:
  - **DB_HOST**: PostgreSQL host url
  - **DB_PORT**: PostgreSQL port
  - **DB_NAME**: PostgreSQL database for catalog
  - **DB_USER**: PostgreSQL user name
  - **DB_PASS**: PostgreSQL user password

## Run locally

*Commands using podman, replace with your container machine*

```sh
# Start database
podman run -d --name foo-db \
  -e POSTGRES_USER=develop \
  -e POSTGRES_PASSWORD=develop \
  -e POSTGRES_DB=foo-db \
  -p 5432:5432 \
  postgres:10.5

# Once the Database is running, start application using Maven
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-DDB_HOST=localhost -DDB_PORT=5432 -DDB_NAME=foo-db -DDB_USER=develop -DDB_PASS=develop"

# Validate
curl localhost:8080/api/foo | jq
# or
curl localhost:8080/actuator/health
```


