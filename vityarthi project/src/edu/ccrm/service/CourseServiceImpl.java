package edu.ccrm.service;
import edu.ccrm.domain.Course;
import java.util.Collection;
public class CourseServiceImpl implements CourseService {
@Override
public void addCourse(Course course) {
DataStore.courses.put(course.getCode(), course);
}
@Override
public Course findCourseByCode(String code) {
return DataStore.courses.get(code);
}
@Override
public Collection<Course> getAllCourses() {
return DataStore.courses.values();
}
}