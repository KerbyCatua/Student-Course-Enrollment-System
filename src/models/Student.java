package models;

public class Student {

    public String fullName;
    public String email;
    public int age;

    Student(String fullName, String email, int age){
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

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
