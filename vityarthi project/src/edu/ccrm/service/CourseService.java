package edu.ccrm.service;
import edu.ccrm.domain.Course;
import java.util.Collection;
public interface CourseService {
void addCourse(Course course);
Course findCourseByCode(String code);
Collection<Course> getAllCourses();
}