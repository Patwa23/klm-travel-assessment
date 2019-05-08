KLM Travel Rest Client
======================

Java RESTtful Oauth2 Client Web service - Consume the mocked services from the service mock project.
The authentication is on application level, so make sure the UI is not bothered with authentication.

#### Add statistics for your backend
- Total number of requests processed
- Total number of requests resulted in an OK response
- Total number of requests resulted in a 4xx response
- Total number of requests resulted in a 5xx response
- Average response time of all requests
- Min response time of all requests
- Max response time of all requests

### Stack
* Java 8
* Spring Boot/MVC/Oauth2 Client
* RESTful
* Lombok
* Commons Lang/Collections
* JUnit 5
* Mockito 2
* Maven 3
* Tomcat 8

### Prerequisites
For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)


### Run the application from command line
Clone this repo and start the travel client rest:

you can clean, compile, run the unit test and  build a single executable JAR file that contains all the necessary dependencies, classes, and resources, 
```
mvn clean install
```
Then you can run the JAR file with:
```
java -jar target/travel-client-rest-0.1.0.jar
```

*Instead of `mvn` you can also use the maven-wrapper `./mvnw` to ensure you have everything necessary to run the Maven build.*
````
./mvnw clean package && java -jar target/travel-client-rest-0.1.0.jar
````

**NOTE:** 
- Docker is the alternative option to run the application
- It can also run using IDE: Run AuthClientApplication main file to start the application

### Run Docker file 
* docker -v
* docker build -f Dockerfile -t travel-client-rest-0.1.0 .
* docker images
* docker run -p 8081:8081 travel-client-rest-0.1.0
* docker ps -a
* docker stop <container-id>

### Monitoring & Management over HTTP 
- http://localhost:9091/client/manage


### Resource endpoints:

**1)Retrieve a list of airports**:

`http://localhost:8081/client/airports`

Query params:

- size: the size of the result
- page: the page to be selected in the paged response
- lang: the language, supported ones are nl and en
- term: A search term that searches through code, name and description.

**2)Retrieve a specific airport**:

`http://localhost:8081/client/airports/{code}`

Query params:

- lang: the language, supported ones are **nl** and **en**

**3)Retrieve a fare offer**:

`http://localhost:8081/client/fares/{origin_code}/{destination_code}`

Query params:

- currency: the requested resulting currency, supported ones are EUR and USD

**4)Display Request/Response**:

`http://localhost:8081/client/trace`

**5)Display static Request/Response**:

`http://localhost:8081/client/traffic`

**6)Display Bar-graph**:

`http://localhost:8081/client/displayBarGraph`




