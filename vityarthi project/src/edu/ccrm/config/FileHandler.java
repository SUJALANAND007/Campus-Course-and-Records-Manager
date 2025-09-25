package edu.ccrm.config;
import edu.ccrm.domain.Course;
import edu.ccrm.domain.Semester;
import edu.ccrm.domain.Student;
import edu.ccrm.service.DataStore;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.List;
public class FileHandler {
public void importStudents(Path path) throws IOException {
List<String> lines = Files.readAllLines(path).stream()
.filter(line -> !line.trim().startsWith("
.collect(Collectors.toList());
for (String line : lines) {
try {
String[] parts = line.split(",");
if (parts.length >= 4) {
String id = parts[0].trim();
String regNo = parts[1].trim();
String name = parts[2].trim();
String email = parts[3].trim();
Student student = new Student(id, name, regNo, email);
DataStore.students.put(student.getRegNo(), student);
}
} catch (Exception e) {
System.err.println("Error processing line: " + line);
e.printStackTrace();
}
}
System.out.println("Imported " + DataStore.students.size() + " students.");
}
public void importCourses(Path path) throws IOException {
List<String> lines = Files.readAllLines(path).stream()
.filter(line -> !line.trim().startsWith("
.collect(Collectors.toList());
for (String line : lines) {
try {
String[] parts = line.split(",");
if (parts.length >= 4) {
String code = parts[0].trim();
String title = parts[1].trim();
int credits = Integer.parseInt(parts[2].trim());
Semester semester = Semester.valueOf(parts[3].trim().toUpperCase());
Course course = new Course.Builder()
.withCode(code)
.withTitle(title)
.withCredits(credits)
.withSemester(semester)
.build();
DataStore.courses.put(course.getCode(), course);
}
} catch (Exception e) {
System.err.println("Error processing line: " + line);
e.printStackTrace();
}
}
System.out.println("Imported " + DataStore.courses.size() + " courses.");
}
public void exportData(Path dir) throws IOException {
if (!Files.exists(dir)) Files.createDirectories(dir);
Path studentsPath = dir.resolve("students_export.csv");
List<String> studentLines = DataStore.students.values().stream()
.map(s -> String.join(",", s.getId(), s.getRegNo(), s.getFullName(), s.getEmail()))
.collect(Collectors.toList());
Files.write(studentsPath, studentLines);
Path coursesPath = dir.resolve("courses_export.csv");
List<String> courseLines = DataStore.courses.values().stream()
.map(c -> String.join(",", c.getCode(), c.getTitle(), String.valueOf(c.getCredits()), c.getSemester().name()))
.collect(Collectors.toList());
Files.write(coursesPath, courseLines);
System.out.println("Data exported successfully to " + dir);
}
public void createBackup() throws IOException {
Path exportDir = Paths.get("export");
if (!Files.exists(exportDir)) {
exportData(exportDir);
}
String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
Path backupDir = Paths.get("backup_" + timestamp);
Files.createDirectories(backupDir);
try (Stream<Path> walk = Files.walk(exportDir)) {
walk.forEach(source -> {
Path destination = backupDir.resolve(exportDir.relativize(source));
try {
Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
} catch (IOException e) {
e.printStackTrace();
}
});
}
System.out.println("Backup created successfully at " + backupDir);
}
}