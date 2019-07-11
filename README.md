# Skywalking demo
![](https://camo.githubusercontent.com/839e5eb1943a8fdb6d0f123c717329a8be504b10/687474703a2f2f736b7977616c6b696e672e6170616368652e6f72672f6173736574732f6672616d652e6a7065673f753d3230313930353138)

## Requirement
* JDK 1.8+
* Gradle 5+
* Docker(with docker-compose)

## Run Middleware in docker
[docker-compose](./docker-compose/README.md)

## Download java agent
* http://skywalking.apache.org/downloads/
    ```bash
    tar xzf apache-skywalking-apm-6.2.0.tar.gz 
    mv -f apache-skywalking-apm-bin/agent ./agent
    rm -rf apache-skywalking-apm-bin
    ```
* Add manually trace in code
	* gradle
	```gradle
	implementation 'org.apache.skywalking:apm-toolkit-trace:6.2.0'
	```
	* Code: `@Trace`, `@TraceCrossThread`, `ActiveSpan`, `TraceContext`
* Add logback trace id
	* gradle
	```gradle
	implementation 'org.apache.skywalking:apm-toolkit-logback-1.x:6.2.0'
	```
	* logback.xml
	```logback
	<conversionRule conversionWord="tid" converterClass="org.apache.skywalking.apm.toolkit.log.logback.v1.x.LogbackPatternConverter"/>
    <property name="CONSOLE_LOG_PATTERN"
              value="%clr([%tid]%d{yyyy-MM-dd HH:mm:ss.SSS}){faint} %clr(${LOG_LEVEL_PATTERN:-%5p}) %clr(${PID:- }){magenta} %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{39}){cyan} %clr(:){faint} %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}"/>
	```
* [Not Recommend]Enable spring annotation trace plugin
    ```bash
     mv agent/optional-plugins/apm-spring-annotation-plugin-6.2.0.jar agent/plugins/
    ```
	
## Enable agent
For gradle as an example
```gradle
bootRun {
	jvmArgs = ["-Dskywalking.agent.service_name=gateway", "-javaagent:$projectDir/agent/skywalking-agent.jar"]
}
```
* `skywalking.agent.service_name` should be same as service name in `application.yml` to avoid some issues.

## Run services
```bash
gradle :gateway:bootRun
gradle :demo-service:bootRun
```

## Test with postman
import `Demo.postman_collection.json` into postman
