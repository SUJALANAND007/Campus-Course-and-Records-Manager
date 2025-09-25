package edu.ccrm.service;
import edu.ccrm.domain.Student;
import java.util.Collection;
import java.util.Optional;
public class StudentServiceImpl implements StudentService {
@Override
public void addStudent(Student student) {
DataStore.students.put(student.getRegNo(), student);
}
@Override
public Student findStudentByRegNo(String regNo) {
return DataStore.students.get(regNo);
}
@Override
public Collection<Student> getAllStudents() {
return DataStore.students.values();
}
}