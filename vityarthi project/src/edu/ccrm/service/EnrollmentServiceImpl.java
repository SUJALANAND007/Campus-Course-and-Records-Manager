package edu.ccrm.service;
import edu.ccrm.domain.Course;
import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Grade;
import edu.ccrm.domain.Student;
public class EnrollmentServiceImpl implements EnrollmentService {
private static final int MAX_CREDITS = 18;
@Override
public void enrollStudent(String regNo, String courseCode)
throws MaxCreditLimitExceededException, DuplicateEnrollmentException {
Student student = DataStore.students.get(regNo);
Course course = DataStore.courses.get(courseCode);
if (student == null || course == null) {
throw new IllegalArgumentException("Invalid Student Registration Number or Course Code.");
}
boolean alreadyEnrolled = student.getEnrolledCourses().stream()
.anyMatch(e -> e.getCourse().getCode().equals(courseCode));
if (alreadyEnrolled) {
throw new DuplicateEnrollmentException("Student is already enrolled in this course.");
}
int currentCredits = student.getEnrolledCourses().stream()
.mapToInt(e -> e.getCourse().getCredits())
.sum();
if (currentCredits + course.getCredits() > MAX_CREDITS) {
throw new MaxCreditLimitExceededException("Enrollment failed: Exceeds max credit limit of " + MAX_CREDITS);
}
Enrollment enrollment = new Enrollment(student, course);
student.addEnrollment(enrollment);
}
@Override
public void assignGrade(String regNo, String courseCode, Grade grade) {
Student student = DataStore.students.get(regNo);
if (student == null) {
System.out.println("Error: Student not found.");
return;
}
student.getEnrolledCourses().stream()
.filter(e -> e.getCourse().getCode().equals(courseCode))
.findFirst()
.ifPresent(enrollment -> enrollment.setGrade(grade));
}
}