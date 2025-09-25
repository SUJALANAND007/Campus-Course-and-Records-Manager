package edu.ccrm.domain;
public abstract class Person {
protected String id;
protected String fullName;
public Person(String id, String fullName) {
this.id = id;
this.fullName = fullName;
}
public String getId() {
return id;
}
public String getFullName() {
return fullName;
}
public abstract String getDetails();
}