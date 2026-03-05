package com.klu;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "student")
public class Student {
    @Id
    private int id;
    private String name;
    private String dept;

    public Student() {}
    public Student(int id, String name, String dept) {
        this.id = id;
        this.name = name;
        this.dept = dept;
    }
    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDept() { return dept; }
    public void setDept(String dept) { this.dept = dept; }
}
