# distributed-tracing-demo
Repository for demostrating how to enable tracing in a Spring Boot microservice.

The /api/foo request executes the following layers:

  @Controller (with a random delay) -> @Service (with a random delay) -> @Repository (with a select all query)

- Configuration via Environment Variables:
  - **DB_HOST**: PostgreSQL host url
  - **DB_PORT**: PostgreSQL port
  - **DB_NAME**: PostgreSQL database for catalog
  - **DB_USER**: PostgreSQL user name
  - **DB_PASS**: PostgreSQL user password

## Run locally

```sh
# Start environment (Postgres and Jaeger)
docker-compose up -d

# Once the everything is running, start application using Maven
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-DDB_HOST=localhost -DDB_PORT=5432 -DDB_NAME=foo-db -DDB_USER=develop -DDB_PASS=develop -Dspring.profiles.active=local"

# Validate
curl localhost:8080/api/foo | jq
# or
curl localhost:8080/actuator/health
```

To review the jaeger traces go to -> http://localhost:16686/ and open 'distributed-tracing-demo' service.

This is an example of an execution:

![local-jaeger-traces](images/local-jaeger-traces.png)

## Run in OpenShift

### Prerequisites

  - Openshift cluster
  - 'oc' tool
  - Install distributed tracing components:
    - Eslasticsearch operator
    - OpenShift distributed tracing platform operator
    - OpenShift distributed tracing data collection operator
    - Jaeger stack

    <span style="color:red">!!!</span> Use [this repository](https://github.com/cmcornejocrespo/distributed-tracing) to automatically install all components.

### Environment

Create application namespace and deploy a postgres instance:
```sh
# Create project
oc new-project app-tracing-demo

# Postgresql
oc new-app postgresql-persistent \
  --param DATABASE_SERVICE_NAME=foo-db \
  --param POSTGRESQL_USER=user \
  --param POSTGRESQL_PASSWORD=pass \
  --param POSTGRESQL_DATABASE=foo-db \
  -n app-tracing-demo
```

### Simple version

<span style="color:red">!!!</span> Remove other installed version in this namespace

```sh
# Deploy
oc apply -k deploy/overlays/simple -n app-tracing-demo

# Wait until build finish
oc logs -f bc/foo-app -n app-tracing-demo

# Delete
oc delete -k deploy/overlays/simple -n app-tracing-demo
```

### Sidecar version

<span style="color:red">!!!</span> Remove other installed version in this namespace

```sh
# Deploy
oc apply -k deploy/overlays/sidecar -n app-tracing-demo

# Wait until build finish
oc logs -f bc/foo-app -n app-tracing-demo

# Delete
oc delete -k deploy/overlays/sidecar -n app-tracing-demo
```

See 'Review Traces' section to test deployment

### Review Traces

- Execute a request:
  ```sh
  FOO_URL=$(oc get route foo-app -o template --template='{{.spec.host}}' -n app-tracing-demo)

  curl http://${FOO_URL}/api/foo | jq

- Review traces in jagger:
  ```sh
  # Get URL
  oc get route simple-prod -o template --template='{{.spec.host}}' -n tracing-system
  ```






