package edu.ccrm.domain;
public class Course {
private final String code;
private final String title;
private final int credits;
private Instructor instructor;
private final Semester semester;
private Course(Builder builder) {
this.code = builder.code;
this.title = builder.title;
this.credits = builder.credits;
this.instructor = builder.instructor;
this.semester = builder.semester;
}
public String getCode() { return code; }
public String getTitle() { return title; }
public int getCredits() { return credits; }
public Instructor getInstructor() { return instructor; }
public Semester getSemester() { return semester; }
public void setInstructor(Instructor instructor) {
this.instructor = instructor;
}
@Override
public String toString() {
String instructorName = (instructor != null) ? instructor.getFullName() : "TBD";
return String.format("Course [Code: %s, Title: '%s', Credits: %d, Semester: %s, Instructor: %s]",
code, title, credits, semester, instructorName);
}
public static class Builder {
private String code;
private String title;
private int credits;
private Instructor instructor;
private Semester semester;
public Builder withCode(String code) {
this.code = code;
return this;
}
public Builder withTitle(String title) {
this.title = title;
return this;
}
public Builder withCredits(int credits) {
this.credits = credits;
return this;
}
public Builder withInstructor(Instructor instructor) {
this.instructor = instructor;
return this;
}
public Builder withSemester(Semester semester) {
this.semester = semester;
return this;
}
public Course build() {
return new Course(this);
}
}
}