package com.upskilling.employee;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.DoubleSummaryStatistics;

public class Main {
    public static void main(String[] args) {
        EmployeeRepository repository = new EmployeeRepository();
        PayrollService payrollService = new PayrollService();

        seedEmployees(repository);
        printDirectory(repository);
        printDepartmentCounts(repository);
        printPayrollSummary(repository, payrollService);
        exportReport(repository);
    }

    private static void seedEmployees(EmployeeRepository repository) {
        repository.add(new Employee(101, "Ananya Rao", "Frontend Engineer", Department.ENGINEERING,
                "Client Portal", LocalDate.of(2024, 6, 10), 72000));
        repository.add(new Employee(102, "Rahul Menon", "Business Analyst", Department.DELIVERY,
                "Claims Modernization", LocalDate.of(2023, 11, 18), 68000));
        repository.add(new Employee(103, "Meera Iyer", "Finance Executive", Department.FINANCE,
                "Payroll Review", LocalDate.of(2025, 1, 9), 54000));
        repository.add(new Employee(104, "Kabir Shah", "HR Partner", Department.HR,
                "Campus Hiring", LocalDate.of(2024, 9, 23), 61000));
        repository.add(new Employee(105, "Nisha Thomas", "QA Engineer", Department.ENGINEERING,
                "Client Portal", LocalDate.of(2025, 5, 14), 58000));
    }

    private static void printDirectory(EmployeeRepository repository) {
        System.out.println("Employee Directory");
        System.out.println("ID  | Name           | Designation        | Department  | Project                | Salary");
        repository.all().forEach(System.out::println);
        System.out.println();

        System.out.println("Engineering Employees");
        repository.findByDepartment(Department.ENGINEERING).forEach(System.out::println);
        System.out.println();
    }

    private static void printDepartmentCounts(EmployeeRepository repository) {
        System.out.println("Headcount by Department");
        repository.countByDepartment()
                .forEach((department, count) -> System.out.println(department + " = " + count));
        System.out.println();
    }

    private static void printPayrollSummary(EmployeeRepository repository, PayrollService payrollService) {
        DoubleSummaryStatistics summary = payrollService.salarySummary(repository.all());

        System.out.println("Payroll Summary");
        System.out.printf("Average monthly salary: %.2f%n", summary.getAverage());
        System.out.printf("Highest monthly salary: %.2f%n", summary.getMax());
        System.out.println();

        System.out.println("Top Paid Employees with Bonus");
        repository.topPaidEmployees(3).forEach(employee ->
                System.out.printf("%s -> annual %.2f, bonus %.2f%n",
                        employee.getName(),
                        employee.annualSalary(),
                        payrollService.calculateBonus(employee)));
        System.out.println();
    }

    private static void exportReport(EmployeeRepository repository) {
        ReportWriter writer = new ReportWriter();
        Path reportPath = Path.of("reports", "employees.csv");

        try {
            writer.writeEmployeeReport(repository.all(), reportPath);
            System.out.println("Report exported to " + reportPath.toAbsolutePath());
        } catch (IOException exception) {
            System.out.println("Could not export report: " + exception.getMessage());
        }
    }
}

