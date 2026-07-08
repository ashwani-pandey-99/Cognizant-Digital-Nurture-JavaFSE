package com.upskilling.employee;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ReportWriter {
    public void writeEmployeeReport(List<Employee> employees, Path path) throws IOException {
        List<String> lines = employees.stream()
                .map(employee -> "%d,%s,%s,%s,%s,%.2f".formatted(
                        employee.getId(),
                        employee.getName(),
                        employee.getDesignation(),
                        employee.getDepartment(),
                        employee.getProjectName(),
                        employee.getMonthlySalary()))
                .toList();

        Files.createDirectories(path.getParent());
        Files.write(path, lines);
    }
}

