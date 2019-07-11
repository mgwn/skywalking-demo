# Docker compose
## Wiremock
```bash
docker-compose -f wiremock.yml up -d 
```
### 准备测试数据
```json
{
    "uuid": "eb46eec5-0299-4be2-bfba-d9e736f983ea",
    "request": {
        "url": "/mock/user/1",
        "method": "POST"
    },
    "response": {
        "status": 200,
        "body": "{\"id\": \"123\", \"name\": \"test\"}",
        "headers": {
            "Content-Type": "application/json"
        }
    }
}
```

## Skywalking
```bash
docker-compose -f skywalking.yml up -d 
```