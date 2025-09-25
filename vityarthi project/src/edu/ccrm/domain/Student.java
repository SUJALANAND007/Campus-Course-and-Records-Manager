package edu.ccrm.domain;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
public class Student extends Person {
private String regNo;
private String email;
private LocalDate registrationDate;
private List<Enrollment> enrolledCourses;
public Student(String id, String fullName, String regNo, String email) {
super(id, fullName);
this.regNo = regNo;
this.email = email;
this.registrationDate = LocalDate.now();
this.enrolledCourses = new ArrayList<>();
}
public String getRegNo() {
return regNo;
}
public String getEmail() {
return email;
}
public LocalDate getRegistrationDate() {
return registrationDate;
}
public List<Enrollment> getEnrolledCourses() {
return enrolledCourses;
}
public void addEnrollment(Enrollment enrollment) {
this.enrolledCourses.add(enrollment);
}
@Override
public String getDetails() {
String courses = enrolledCourses.stream()
.map(e -> e.getCourse().getTitle())
.collect(Collectors.joining(", "));
return String.format("Student [ID: %s, Name: %s, RegNo: %s, Email: %s, Enrolled In: %s]",
id, fullName, regNo, email, courses.isEmpty() ? "None" : courses);
}
@Override
public String toString() {
return String.format("%s (%s)", fullName, regNo);
}
}