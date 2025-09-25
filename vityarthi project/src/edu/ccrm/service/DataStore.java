package edu.ccrm.service;
import edu.ccrm.domain.Course;
import edu.ccrm.domain.Instructor;
import edu.ccrm.domain.Student;
import java.util.HashMap;
import java.util.Map;
public class DataStore {
public static final Map<String, Student> students = new HashMap<>();
public static final Map<String, Course> courses = new HashMap<>();
public static final Map<String, Instructor> instructors = new HashMap<>();
}