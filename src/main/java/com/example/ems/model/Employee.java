package com.example.ems.model;


public class Employee {
    private int id;
    private String name;
    private String email;
    private String position;
    private int departmentId;

    public Employee(int id, String name, String email, String position, int departmentId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.position = position;
        this.departmentId = departmentId;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public int getDepartmentId() { return departmentId; }
    public void setDepartmentId(int departmentId) { this.departmentId = departmentId; }


}
