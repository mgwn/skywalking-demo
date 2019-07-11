# Skywalking demo
![](https://camo.githubusercontent.com/839e5eb1943a8fdb6d0f123c717329a8be504b10/687474703a2f2f736b7977616c6b696e672e6170616368652e6f72672f6173736574732f6672616d652e6a7065673f753d3230313930353138)

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