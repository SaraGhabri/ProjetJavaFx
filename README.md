

# Employee Management System (EMS)

## Overview

The **Employee Management System (EMS)** is a JavaFX application designed to manage employee data, departments, and attendance. The system allows users to:

- Add, edit, and delete employee records.
- Add, edit, and delete department records.
- Track employee attendance (marking present, excused absence, or unexcused absence).
- Display employee and department data in tables.
  
The system connects to a MySQL database to store employee and department information and utilizes JavaFX for the user interface.

---

## Functionalities

### 1. **Employee Management**
   - **Add Employee**: Add a new employee with details such as name, email, position, and department ID.
   - **Edit Employee**: Modify an existing employee's details (name, email, position, department).
   - **Delete Employee**: Remove an employee from the system.
   - **View Employees**: Display a list of all employees with their details, including name, email, position, and department.

### 2. **Department Management**
   - **Add Department**: Add a new department with a name.
   - **Edit Department**: Modify an existing department's name.
   - **Delete Department**: Remove a department from the system.
   - **View Departments**: Display a list of all departments.

### 3. **Attendance Management**
   - **Mark Attendance**: Track employee attendance by marking as present, excused absence, or unexcused absence.
   - **Alert System**: Notify when an employee has exceeded two unexcused absences in a week.

---

## Technologies Used

- **Java**: The core language used to build the application.
- **JavaFX**: For building the GUI (graphical user interface).
- **MySQL**: For managing and storing data.
- **JDBC (Java Database Connectivity)**: For connecting the Java application to the MySQL database.
- **PHPMyAdmin**: For database management and visual interface to interact with MySQL.

---

## Configurations

### 1. **Database Setup**
   - You must set up a MySQL database with the following schema and tables:

   **Database Name**: `employee_management`
   
   **Tables**:

   1. **Employee Table**:
      - `id` (INT, AUTO_INCREMENT, Primary Key)
      - `name` (VARCHAR)
      - `email` (VARCHAR)
      - `position` (VARCHAR)
      - `department_id` (INT, Foreign Key referencing Department)

   2. **Department Table**:
      - `id` (INT, AUTO_INCREMENT, Primary Key)
      - `name` (VARCHAR)

   3. **Attendance Table**:
      - `id` (INT, AUTO_INCREMENT, Primary Key)
      - `employee_id` (INT, Foreign Key referencing Employee)
      - `attendance_date` (DATE)
      - `status` (ENUM: 'present', 'excused', 'unexcused')

   - **Create Database**: Use PHPMyAdmin or MySQL commands to create the database `employee_management` and the above tables.

   - **Establish JDBC Connection**:
     - Ensure the **MySQL JDBC driver** is added to your project dependencies.
     - Update the `DB_URL`, `DB_USERNAME`, and `DB_PASSWORD` in your project configuration to match your MySQL database credentials.
     
     Example JDBC URL:
     ```java
     String dbURL = "jdbc:mysql://localhost:3306/employee_management";
     String dbUsername = "your_mysql_username";
     String dbPassword = "your_mysql_password";
     ```

### 2. **IDE Setup**
   - **IntelliJ IDEA Setup**:
     1. Make sure IntelliJ IDEA is installed with Java 11 or above.
     2. Install the necessary JavaFX SDK and add it to your project dependencies.
     3. Ensure you have the correct MySQL JDBC driver in your project libraries (download from [MySQL's website](https://dev.mysql.com/downloads/connector/j/)).

### 3. **Running the Project**
   - **Database Connection**: Before running the project, ensure the MySQL server is running and the database is configured.
   - **Run the Application**:
     1. Open IntelliJ IDEA and load the project.
     2. Run the `HelloApplication.java` main class.
     3. The JavaFX interface will open, allowing you to manage employees, departments, and attendance.

### 4. **Dependencies**
   - **MySQL JDBC Driver**: You must include the MySQL JDBC driver for database connection.
   - **JavaFX SDK**: If not bundled with your JDK, download and configure JavaFX.

---

## Directory Structure

```
EmployeeManagementSystem/
├── src/
│   ├── com/
│   │   ├── example/
│   │   │   ├── ems/
│   │   │   │   ├── controller/
│   │   │   │   │   ├── EmployeeController.java
│   │   │   │   │   ├── DepartmentController.java
│   │   │   │   │   └── AttendanceController.java
│   │   │   │   ├── model/
│   │   │   │   │   ├── Employee.java
│   │   │   │   │   ├── Department.java
│   │   │   │   │   └── Attendance.java
│   │   │   │   ├── view/
│   │   │   │   │   ├── EmployeeView.fxml
│   │   │   │   │   ├── DepartmentView.fxml
│   │   │   │   │   └── AttendanceView.fxml
│   │   │   └── HelloApplication.java
└── resources/
    ├── com/
    │   └── example/
    │       └── tutojavafx/
    │           └── hello-view.fxml
```

---

