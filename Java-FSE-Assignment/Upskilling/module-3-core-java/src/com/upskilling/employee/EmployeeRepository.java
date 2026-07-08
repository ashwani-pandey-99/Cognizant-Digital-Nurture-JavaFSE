package com.upskilling.employee;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class EmployeeRepository {
    private final List<Employee> employees = new ArrayList<>();

    public void add(Employee employee) {
        boolean duplicate = employees.stream().anyMatch(item -> item.getId() == employee.getId());
        if (duplicate) {
            throw new InvalidEmployeeException("Employee id already exists: " + employee.getId());
        }
        employees.add(employee);
    }

    public Optional<Employee> findById(int id) {
        return employees.stream()
                .filter(employee -> employee.getId() == id)
                .findFirst();
    }

    public List<Employee> findByDepartment(Department department) {
        return employees.stream()
                .filter(employee -> employee.getDepartment() == department)
                .sorted()
                .toList();
    }

    public List<Employee> topPaidEmployees(int limit) {
        return employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getMonthlySalary).reversed())
                .limit(limit)
                .toList();
    }

    public Map<Department, Long> countByDepartment() {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
    }

    public List<Employee> all() {
        return employees.stream()
                .sorted()
                .toList();
    }
}

