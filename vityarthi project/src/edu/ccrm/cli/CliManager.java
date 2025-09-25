package edu.ccrm.cli;
import edu.ccrm.config.AppConfig;
import edu.ccrm.domain.*;
import edu.ccrm.config.FileHandler;
import edu.ccrm.service.*;
import edu.ccrm.util.DirectoryUtils;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;
import java.util.Scanner;
public class CliManager {
private final Scanner scanner = new Scanner(System.in);
private final StudentService studentService = new StudentServiceImpl();
private final CourseService courseService = new CourseServiceImpl();
private final EnrollmentService enrollmentService = new EnrollmentServiceImpl();
private final TranscriptService transcriptService = new TranscriptService();
private final FileHandler fileHandler = new FileHandler();
public void run() {
boolean running = true;
do {
displayMainMenu();
int choice = Integer.parseInt(scanner.nextLine());
switch (choice) {
case 1 -> manageStudents();
case 2 -> manageCourses();
case 3 -> manageEnrollment();
case 4 -> manageData();
case 5 -> performBackup();
case 6 -> running = false;
default -> System.out.println("Invalid choice. Please try again.");
}
} while (running);
System.out.println("Exiting CCRM. Goodbye!");
}
private void displayMainMenu() {
System.out.println("\n===== Campus Course & Records Manager =====");
System.out.println("1. Manage Students");
System.out.println("2. Manage Courses");
System.out.println("3. Manage Enrollment & Grades");
System.out.println("4. Import/Export Data");
System.out.println("5. Backup Data & View Size");
System.out.println("6. Exit");
System.out.print("Enter your choice: ");
}
private void manageStudents() {
System.out.println("\n--- Manage Students ---");
System.out.println("1. Add New Student");
System.out.println("2. List All Students");
System.out.println("3. View Student Transcript");
System.out.print("Enter your choice: ");
int choice = Integer.parseInt(scanner.nextLine());
switch (choice) {
case 1 -> {
System.out.print("Enter ID: "); String id = scanner.nextLine();
System.out.print("Enter Full Name: "); String name = scanner.nextLine();
System.out.print("Enter Reg No: "); String regNo = scanner.nextLine();
System.out.print("Enter Email: "); String email = scanner.nextLine();
studentService.addStudent(new Student(id, name, regNo, email));
System.out.println("Student added successfully.");
}
case 2 -> {
System.out.println("\n--- List of All Students ---");
studentService.getAllStudents().stream()
.sorted(Comparator.comparing(Person::getFullName))
.forEach(s -> System.out.println(s.getDetails()));
}
case 3 -> {
System.out.print("Enter Student Reg No: ");
String regNo = scanner.nextLine();
Student student = studentService.findStudentByRegNo(regNo);
if (student != null) {
System.out.println(transcriptService.generateTranscript(student));
} else {
System.out.println("Student not found.");
}
}
}
}
private void manageCourses() {
System.out.println("\n--- List of All Courses ---");
courseService.getAllCourses().forEach(System.out::println);
}
private void manageEnrollment() {
System.out.println("\n--- Manage Enrollment & Grades ---");
System.out.println("1. Enroll Student in Course");
System.out.println("2. Assign Grade to Student");
System.out.print("Enter your choice: ");
int choice = Integer.parseInt(scanner.nextLine());
System.out.print("Enter Student Reg No: ");
String regNo = scanner.nextLine();
System.out.print("Enter Course Code: ");
String courseCode = scanner.nextLine();
main_loop:
switch (choice) {
case 1 -> {
try {
enrollmentService.enrollStudent(regNo, courseCode);
System.out.println("Enrollment successful!");
} catch (MaxCreditLimitExceededException | DuplicateEnrollmentException e) {
System.out.println("Error: " + e.getMessage());
} catch (IllegalArgumentException e) {
System.out.println("Error: " + e.getMessage());
break main_loop;
}
}
case 2 -> {
System.out.print("Enter Grade (S, A, B, etc.): ");
Grade grade = Grade.valueOf(scanner.nextLine().toUpperCase());
enrollmentService.assignGrade(regNo, courseCode, grade);
System.out.println("Grade assigned successfully.");
}
}
}
private void manageData() {
System.out.println("\n--- Import/Export Data ---");
System.out.println("1. Import Students from CSV");
System.out.println("2. Import Courses from CSV");
System.out.print("Enter your choice: ");
int choice = Integer.parseInt(scanner.nextLine());
Path dataDir = AppConfig.getInstance().getDataDirectory();
try {
switch(choice) {
case 1 -> fileHandler.importStudents(dataDir.resolve("students.csv"));
case 2 -> fileHandler.importCourses(dataDir.resolve("courses.csv"));
}
} catch (IOException e) {
System.err.println("File operation failed: " + e.getMessage());
}
}
private void performBackup() {
try {
System.out.println("Creating backup...");
fileHandler.createBackup();
Path backupDir = Paths.get("backup");
if (Files.exists(backupDir)) {
System.out.println("Calculating size of backup directory...");
long size = DirectoryUtils.calculateSize(backupDir);
System.out.printf("Backup size: %.2f KB\n", size / 1024.0);
}
} catch (IOException e) {
System.err.println("Backup operation failed: " + e.getMessage());
e.printStackTrace();
}
}
}