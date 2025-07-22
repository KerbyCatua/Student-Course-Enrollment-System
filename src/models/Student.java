package models;

public class Student {

    public String fullName;
    public String email;
    public int age;

    public Student(String fullName, String email, int age){
        this.fullName = fullName;
        this.email = email;
        this.age = age;
    }
    
    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

}
