# Sunbase-Api-Integration-Assignment

This project is a simple web application for managing customers. It provides functionalities like adding, deleting, updating, and listing customers using REST APIs provided by SunbaseData.

## Technologies Used

- Java
- Spring Boot
- Hibernate
- RESTful APIs
- HTML/CSS (Basic UI)

## Project Structure

The project structure is organized as follows:

- `src/main/java`: Contains Java source code.
  - `com.sunbaseData.assignment.Assignment.controller`: Contains controllers for handling web requests.
  - `com.sunbaseData.assignment.Assignment.model`: Contains entity classes representing data models.
  - `com.sunbaseData.assignment.Assignment.service`: Contains service classes for API interaction.
  - `com.sunbaseData.assignment.Assignment`: Contains the main application class.
- `src/main/resources`: Contains application properties and static resources.
  - `templates`: Contains HTML templates for UI.

## How to Run the Application

1. Clone this repository.

2. Open the project in your preferred IDE (Integrated Development Environment).

3. Set up the database:
   - Make sure MySQL is installed.
   - Update `application.properties` with your MySQL credentials and database details.

4. Build the project using Maven.

5. Run the `AssignmentApplication.java` file to start the Spring Boot application.

6. Access the application using a web browser: [http://localhost:8080](http://localhost:8080)

## Endpoints

- `/login`: Login page.
- `/customer-list`: View list of customers.
- `/add-customer`: Add a new customer.

## API Integration

- APIs for creating, deleting, updating, and listing customers are integrated with SunbaseData RESTful APIs.
- Ensure proper network connectivity to SunbaseData's API endpoint for functionality.

## Additional Notes

- For security reasons, consider using environment variables or a more secure approach for handling sensitive information like API tokens and credentials.

## Contact

For questions or feedback, please contact [Amit Ashok Swain](mailto:business.amitswain@gmail.com).

Feel free to contribute or provide feedback on improving this application!

