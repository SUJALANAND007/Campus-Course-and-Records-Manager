package edu.ccrm.domain;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
public class Instructor extends Person {
private String department;
private List<Course> assignedCourses;
public Instructor(String id, String fullName, String department) {
super(id, fullName);
this.department = department;
this.assignedCourses = new ArrayList<>();
}
public String getDepartment() {
return department;
}
public List<Course> getAssignedCourses() {
return assignedCourses;
}
public void assignCourse(Course course) {
this.assignedCourses.add(course);
}
@Override
public String getDetails() {
String courses = assignedCourses.stream()
.map(Course::getCode)
.collect(Collectors.joining(", "));
return String.format("Instructor [ID: %s, Name: %s, Dept: %s, Teaches: %s]",
id, fullName, department, courses.isEmpty() ? "None" : courses);
}
@Override
public String toString() {
return String.format("%s (%s Dept)", fullName, department);
}
}