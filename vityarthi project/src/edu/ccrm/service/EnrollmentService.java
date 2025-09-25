package edu.ccrm.service;
import edu.ccrm.domain.Grade;
public interface EnrollmentService {
void enrollStudent(String regNo, String courseCode)
throws MaxCreditLimitExceededException, DuplicateEnrollmentException;
void assignGrade(String regNo, String courseCode, Grade grade);
}