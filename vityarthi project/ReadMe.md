// CCRM_Project/README.md
# Campus Course & Records Manager (CCRM)

## 1. Project Overview
CCRM is a console-based Java application for managing student and course records. It's built to demonstrate a comprehensive range of Java SE features, from Object-Oriented Programming principles to modern APIs like NIO.2 and Streams.

## 2. How to Run
- **JDK Version:** Java 17+
- **Compile and Run from project root:**
  ```bash
  # Compile
  javac -d out $(find src -name "*.java")
  # Run
  java -cp out edu.ccrm.cli.Main