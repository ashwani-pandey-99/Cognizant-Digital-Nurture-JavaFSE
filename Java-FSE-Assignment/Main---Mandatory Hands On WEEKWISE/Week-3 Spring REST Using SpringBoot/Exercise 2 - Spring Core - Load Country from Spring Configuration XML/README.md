# Exercise 2 - Spring Core - Load Country from Spring Configuration XML

## Project Details
- **Project Name**: Exercise 2 - Spring Core - Load Country from Spring Configuration XML
- **Group ID**: com.cognizant
- **Artifact ID**: spring-learn
- **Package Name**: com.cognizant.springlearn
- **Java Version**: 17
- **Version**: 1.0

## Dependencies
1. **spring-context** (6.1.8) - Core Spring IoC container
2. **slf4j-api** (2.0.13) - Logging API
3. **logback-classic** (1.5.6) - Logging implementation

## Project Structure
```
exercise-2-spring-core/
├── pom.xml
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── cognizant
    │   │           └── springlearn
    │   │               ├── Country.java
    │   │               └── SpringLearnApplication.java
    │   └── resources
    │       └── country.xml
    └── test
        └── java
```

## Key Components

### 1. Country.java
- **POJO Class** with code and name properties
- **Constructor**: Prints "Inside Country Constructor"  
- **Getters/Setters**: Print debug messages when called
- **toString()**: Uses getters to display country information

### 2. country.xml (Spring Configuration)
- **XML-based Bean Configuration**
- Defines a bean with id="country"
- Sets properties: code="IN", name="India"
- Uses property injection via setters

### 3. SpringLearnApplication.java
- **Main Application Class**
- Creates ApplicationContext from XML
- Retrieves Country bean using getBean()
- Displays the country object
- Closes the context properly

## How to Build and Run

### 1. Build the Project
```bash
mvn clean package
```

### 2. Run the Application
```bash
mvn exec:java
```

### 3. Alternative Run Method
```bash
java -cp target/classes:target/dependency/* com.cognizant.springlearn.SpringLearnApplication
```

## Expected Output
```
Inside Country Constructor
Setter called for code
Setter called for name
Getter called for code  
Getter called for name
Country : Country{code='IN', name='India'}
```

## Output Explanation

1. **"Inside Country Constructor"** - Spring creates Country instance
2. **"Setter called for code"** - Spring injects "IN" via setCode()
3. **"Setter called for name"** - Spring injects "India" via setName()  
4. **"Getter called for code"** - toString() calls getCode()
5. **"Getter called for name"** - toString() calls getName()
6. **Final Result** - Country object with code='IN', name='India'

## Spring Concepts Demonstrated

### 1. Inversion of Control (IoC)
- Spring container manages object creation
- Object dependencies injected by framework

### 2. Dependency Injection
- Property-based injection using setter methods
- XML configuration defines dependencies

### 3. Bean Lifecycle
- Constructor called first
- Properties set via setters
- Bean ready for use

### 4. ApplicationContext
- Spring container implementation
- Loads configuration from XML
- Manages bean lifecycle

## Build Verification
✅ **Maven Build**: SUCCESS  
✅ **Application Run**: SUCCESS  
✅ **Output**: Matches expected result  
✅ **Spring Container**: Properly initialized and closed

## Key Learning Points
- XML-based Spring configuration
- Property injection using setters
- ApplicationContext usage
- Bean lifecycle understanding
- Proper resource management (context.close())