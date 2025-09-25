package edu.ccrm.service;
import edu.ccrm.domain.Student;
import java.util.Collection;
public interface StudentService {
void addStudent(Student student);
Student findStudentByRegNo(String regNo);
Collection<Student> getAllStudents();
}