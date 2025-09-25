package edu.ccrm.service;
import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Student;
public class TranscriptService {
public double calculateGPA(Student student) {
if (student.getEnrolledCourses().isEmpty()) {
return 0.0;
}
int totalCredits = 0;
double totalPoints = 0.0;
for (Enrollment e : student.getEnrolledCourses()) {
if (e.getGrade() != null) {
int credits = e.getCourse().getCredits();
totalCredits += credits;
totalPoints += e.getGrade().getPoints() * credits;
}
}
return (totalCredits == 0) ? 0.0 : totalPoints / totalCredits;
}
public String generateTranscript(Student student) {
StringBuilder sb = new StringBuilder();
sb.append("========================================\n");
sb.append("           ACADEMIC TRANSCRIPT          \n");
sb.append("========================================\n");
sb.append("Student Name: ").append(student.getFullName()).append("\n");
sb.append("Reg. No.:     ").append(student.getRegNo()).append("\n");
sb.append("----------------------------------------\n");
sb.append(String.format("%-10s %-30s %-8s %-5s\n", "CODE", "COURSE TITLE", "CREDITS", "GRADE"));
sb.append("----------------------------------------\n");
for (Enrollment enrollment : student.getEnrolledCourses()) {
sb.append(String.format("%-10s %-30s %-8d %-5s\n",
enrollment.getCourse().getCode(),
enrollment.getCourse().getTitle(),
enrollment.getCourse().getCredits(),
enrollment.getGrade() != null ? enrollment.getGrade().name() : "IP"));
}
sb.append("----------------------------------------\n");
sb.append(String.format("GPA: %.2f\n", calculateGPA(student)));
sb.append("========================================\n");
return sb.toString();
}
}