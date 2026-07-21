# Spring Boot Maven Project - Exercise 1

## Project Details
- **Project Name**: spring-learn
- **Group ID**: com.cognizant
- **Artifact ID**: spring-learn
- **Package Name**: com.cognizant.springlearn
- **Build Tool**: Maven
- **Language**: Java
- **Java Version**: 17
- **Packaging**: JAR

## Dependencies Added
1. **Spring Web** - For web development and REST APIs
2. **Spring Boot DevTools** - For hot reloading during development

## Project Structure
```
spring-learn/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── cognizant/
│   │   │           └── springlearn/
│   │   │               └── SpringLearnApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
│           └── com/
│               └── cognizant/
│                   └── springlearn/
│                       └── SpringLearnApplicationTests.java
```

## How to Run

### 1. Build the Project
```bash
mvn clean package
```

### 2. Run the Application
```bash
mvn spring-boot:run
```

### 3. Alternative Run Method
```bash
java -jar target/spring-learn-0.0.1-SNAPSHOT.jar
```

## Application Verification
- Application starts on **port 8080**
- Console output shows:
  - "Spring Boot Application Started"
  - "Application Running Successfully"
  - Tomcat server starts successfully
  - No errors in startup

## Project Components Explained

### 1. src/main/java
Contains the main application source code:
- **SpringLearnApplication.java**: Main class with @SpringBootApplication annotation

### 2. src/main/resources  
Contains configuration files:
- **application.properties**: Configuration settings (currently empty)

### 3. src/test/java
Contains test classes:
- **SpringLearnApplicationTests.java**: Basic context loading test

### 4. pom.xml
Maven configuration file containing:
- Project metadata (groupId, artifactId, version)
- Dependencies (Spring Web, DevTools, Test)
- Build plugins (Spring Boot Maven Plugin)

## Key Annotations and Methods

### @SpringBootApplication
- Combines @Configuration, @EnableAutoConfiguration, @ComponentScan
- Entry point for Spring Boot application
- Enables auto-configuration based on classpath dependencies

### SpringApplication.run()
- Bootstraps and launches the Spring application
- Creates ApplicationContext
- Starts embedded server (Tomcat on port 8080)
- Registers shutdown hooks

## Build Success Verification
✅ Maven build: **SUCCESS**  
✅ Tests run: **1, Failures: 0, Errors: 0**  
✅ Application startup: **SUCCESS**  
✅ Server running on: **http://localhost:8080**  

## Expected Console Output
```
Spring Boot Application Started

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.2.0)

...
Tomcat started on port 8080 (http) with context path ''
Started SpringLearnApplication in X.XXX seconds
Application Running Successfully
```