# Skywalking demo
## Requirement
* JDK 1.8+
* Gradle 5+
* Docker(with docker-compose)

## Run Middleware in docker
[docker-compose](./docker-compose/README.md)

## Run services
```bash
gradle :gateway:bootRun
gradle :demo-service:bootRun
```

## Test with postman
import `Demo.postman_collection.json` into postman
