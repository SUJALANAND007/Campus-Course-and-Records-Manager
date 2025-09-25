# Campus Course & Records Manager (CCRM)

## 1. Project Overview
CCRM (Campus Course & Records Manager) is a comprehensive console-based Java application designed for educational institutions to manage student records, course enrollments, and academic progress. The application showcases core Java SE features and follows industry best practices in software development.

### Key Features:
- Student record management (CRUD operations)
- Course enrollment and grade tracking
- Credit hour management with validation
- Exception handling for business rules
- File-based data persistence
- Command-line interface

## 2. How to Run
### Prerequisites:
- **JDK Version:** Java 17 or later
- **Operating System:** Windows/Linux/macOS
- **Memory:** Minimum 2GB RAM recommended

### Command Line Execution:
```bash
# Navigate to project directory
cd /path/to/vityarthi-project

# Compile all Java files
javac -d out $(find src -name "*.java")

# Run the application
java -cp out edu.ccrm.cli.Main

# Run with assertions enabled (for development)
java -ea -cp out edu.ccrm.cli.Main
```

### Common Commands:
- `add student [name] [id]` - Add a new student
- `enroll [studentId] [courseCode]` - Enroll student in a course
- `assign grade [studentId] [courseCode] [grade]` - Assign grade to student
- `view student [id]` - View student details
- `list courses` - List all available courses
- `exit` - Exit the application

## 3. Project Structure
```
src/
├── edu/
│   └── ccrm/
│       ├── cli/           # Command-line interface components
│       ├── domain/        # Core domain models (Student, Course, etc.)
│       ├── service/       # Business logic and services
│       ├── util/          # Utility classes
│       └── Main.java      # Application entry point
```

## 4. Development Setup
### Eclipse IDE Configuration:
1. **Import Project:**
   - File → Import → Existing Projects into Workspace
   - Select the project root directory
   - Click 'Finish'

2. **Configure Java Build Path:**
   - Right-click project → Properties
   - Java Build Path → Libraries → Add Library → JRE System Library
   - Select JavaSE-17 or higher

3. **Enable Assertions:**
   - Run → Run Configurations...
   - Select Java Application → New Configuration
   - In 'VM arguments' add: `-ea`

## 5. Key Java Concepts Demonstrated
| Concept | Implementation Location |
|---------|-------------------------|
| OOP Principles | `domain/Person.java`, `domain/Student.java` |
| Exception Handling | `service/DuplicateEnrollmentException.java`, `service/MaxCreditLimitExceededException.java` |
| Collections Framework | Service layer implementations |
| File I/O | `util/FileHandler.java` |
| Enums | `domain/Grade.java` |
| Inheritance & Polymorphism | `domain/Person` base class |
| Encapsulation | All domain classes with private fields and public methods |

## 6. Error Handling
The application implements comprehensive error handling:
- **Input Validation**: Validates all user inputs
- **Business Rules**: Enforces academic policies (e.g., max credits per semester)
- **File Operations**: Handles file I/O exceptions gracefully

## 7. Testing
To run unit tests:
```bash
# Compile test files
javac -cp out:lib/* -d out/test $(find test -name "*.java")

# Run tests
java -cp out:out/test org.junit.runner.JUnitCore edu.ccrm.AllTests
```

## 8. Contributing
1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 9. License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 10. Support
For support, please contact [Your Email] or open an issue in the repository.
