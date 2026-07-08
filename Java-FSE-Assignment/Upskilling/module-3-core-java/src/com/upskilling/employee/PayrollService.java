package com.upskilling.employee;

import java.util.DoubleSummaryStatistics;
import java.util.List;

public class PayrollService {
    public double calculateBonus(Employee employee) {
        double baseRate = switch (employee.getDepartment()) {
            case ENGINEERING -> 0.12;
            case DELIVERY -> 0.10;
            case FINANCE -> 0.08;
            case HR -> 0.07;
        };

        double loyaltyRate = employee.completedYears() >= 2 ? 0.03 : 0.01;
        return employee.annualSalary() * (baseRate + loyaltyRate);
    }

    public DoubleSummaryStatistics salarySummary(List<Employee> employees) {
        return employees.stream()
                .mapToDouble(Employee::getMonthlySalary)
                .summaryStatistics();
    }
}

