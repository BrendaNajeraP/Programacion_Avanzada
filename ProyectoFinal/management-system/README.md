# Management System Project

This project is a Management System built using the Model-View-Controller (MVC) design pattern in Java. It provides a structured approach to managing user data and interactions.

## Project Structure

```
management-system
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── controller
│   │   │   │   └── MainController.java
│   │   │   ├── model
│   │   │   │   └── User.java
│   │   │   ├── view
│   │   │   │   └── MainView.java
│   │   │   ├── dao
│   │   │   │   └── UserDAO.java
│   │   │   ├── util
│   │   │   │   └── DBConnection.java
│   │   │   └── App.java
│   │   └── resources
│   │       └── config.properties
│   └── test
│       └── java
│           └── AppTest.java
├── pom.xml
└── README.md
```

## Features

- **User Management**: Create, read, update, and delete user information.
- **MVC Architecture**: Separates concerns for better maintainability and scalability.
- **Database Connectivity**: Utilizes a database for persistent storage of user data.

## Getting Started

1. Clone the repository.
2. Navigate to the project directory.
3. Build the project using Maven: `mvn clean install`.
4. Run the application: `mvn exec:java -Dexec.mainClass="App"`.

## Configuration

Edit the `src/main/resources/config.properties` file to set up your database connection details.

## Testing

Unit tests are located in the `src/test/java` directory. Run tests using Maven: `mvn test`.

## License

This project is licensed under the MIT License.