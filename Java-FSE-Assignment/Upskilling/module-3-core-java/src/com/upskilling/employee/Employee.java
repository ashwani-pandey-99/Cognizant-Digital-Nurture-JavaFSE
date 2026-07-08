package com.upskilling.employee;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class Employee implements Comparable<Employee> {
    private final int id;
    private String name;
    private String designation;
    private Department department;
    private String projectName;
    private LocalDate hireDate;
    private double monthlySalary;

    public Employee(int id, String name, String designation, Department department,
                    String projectName, LocalDate hireDate, double monthlySalary) {
        this.id = id;
        setName(name);
        setDesignation(designation);
        setDepartment(department);
        setProjectName(projectName);
        setHireDate(hireDate);
        setMonthlySalary(monthlySalary);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new InvalidEmployeeException("Employee name cannot be empty");
        }
        this.name = name.trim();
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        if (designation == null || designation.isBlank()) {
            throw new InvalidEmployeeException("Designation cannot be empty");
        }
        this.designation = designation.trim();
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = Objects.requireNonNull(department, "Department is required");
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        if (projectName == null || projectName.isBlank()) {
            throw new InvalidEmployeeException("Project name cannot be empty");
        }
        this.projectName = projectName.trim();
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        if (hireDate == null || hireDate.isAfter(LocalDate.now())) {
            throw new InvalidEmployeeException("Hire date must be valid");
        }
        this.hireDate = hireDate;
    }

    public double getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(double monthlySalary) {
        if (monthlySalary < 15000) {
            throw new InvalidEmployeeException("Monthly salary must be at least 15000");
        }
        this.monthlySalary = monthlySalary;
    }

    public double annualSalary() {
        return monthlySalary * 12;
    }

    public int completedYears() {
        return Period.between(hireDate, LocalDate.now()).getYears();
    }

    @Override
    public int compareTo(Employee other) {
        return this.name.compareToIgnoreCase(other.name);
    }

    @Override
    public String toString() {
        return "%d | %-14s | %-18s | %-11s | %-22s | %.2f"
                .formatted(id, name, designation, department, projectName, monthlySalary);
    }
}

