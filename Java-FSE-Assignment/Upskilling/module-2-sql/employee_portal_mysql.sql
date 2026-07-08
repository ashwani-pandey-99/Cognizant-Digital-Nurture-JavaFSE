DROP DATABASE IF EXISTS upskilling_employee_portal;
CREATE DATABASE upskilling_employee_portal;
USE upskilling_employee_portal;

CREATE TABLE departments (
  department_id INT PRIMARY KEY AUTO_INCREMENT,
  department_name VARCHAR(60) NOT NULL UNIQUE,
  location VARCHAR(60) NOT NULL
);

CREATE TABLE projects (
  project_id INT PRIMARY KEY AUTO_INCREMENT,
  project_name VARCHAR(80) NOT NULL,
  client_name VARCHAR(80) NOT NULL,
  start_date DATE NOT NULL,
  end_date DATE NULL,
  budget DECIMAL(12, 2) NOT NULL CHECK (budget > 0)
);

CREATE TABLE employees (
  employee_id INT PRIMARY KEY AUTO_INCREMENT,
  employee_name VARCHAR(80) NOT NULL,
  email VARCHAR(120) NOT NULL UNIQUE,
  department_id INT NOT NULL,
  project_id INT NULL,
  designation VARCHAR(80) NOT NULL,
  hire_date DATE NOT NULL,
  salary DECIMAL(10, 2) NOT NULL CHECK (salary >= 15000),
  status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
  CONSTRAINT fk_employee_department
    FOREIGN KEY (department_id) REFERENCES departments(department_id),
  CONSTRAINT fk_employee_project
    FOREIGN KEY (project_id) REFERENCES projects(project_id)
);

CREATE TABLE attendance (
  attendance_id INT PRIMARY KEY AUTO_INCREMENT,
  employee_id INT NOT NULL,
  work_date DATE NOT NULL,
  hours_worked DECIMAL(4, 2) NOT NULL CHECK (hours_worked BETWEEN 0 AND 12),
  CONSTRAINT fk_attendance_employee
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id),
  CONSTRAINT uq_attendance_day UNIQUE (employee_id, work_date)
);

CREATE TABLE salary_audit (
  audit_id INT PRIMARY KEY AUTO_INCREMENT,
  employee_id INT NOT NULL,
  old_salary DECIMAL(10, 2) NOT NULL,
  new_salary DECIMAL(10, 2) NOT NULL,
  changed_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO departments (department_name, location) VALUES
('Engineering', 'Chennai'),
('Finance', 'Bengaluru'),
('HR', 'Hyderabad'),
('Delivery', 'Pune');

INSERT INTO projects (project_name, client_name, start_date, end_date, budget) VALUES
('Client Portal', 'Northwind Bank', '2026-01-15', NULL, 1800000.00),
('Payroll Review', 'Internal', '2026-02-01', '2026-08-31', 450000.00),
('Claims Modernization', 'Apex Insurance', '2026-03-10', NULL, 2200000.00),
('Campus Hiring', 'Internal', '2026-04-05', '2026-07-30', 300000.00);

INSERT INTO employees
  (employee_name, email, department_id, project_id, designation, hire_date, salary, status)
VALUES
('Ananya Rao', 'ananya.rao@example.com', 1, 1, 'Frontend Engineer', '2024-06-10', 72000.00, 'ACTIVE'),
('Rahul Menon', 'rahul.menon@example.com', 4, 3, 'Business Analyst', '2023-11-18', 68000.00, 'ACTIVE'),
('Meera Iyer', 'meera.iyer@example.com', 2, 2, 'Finance Executive', '2025-01-09', 54000.00, 'ACTIVE'),
('Kabir Shah', 'kabir.shah@example.com', 3, 4, 'HR Partner', '2024-09-23', 61000.00, 'ACTIVE'),
('Nisha Thomas', 'nisha.thomas@example.com', 1, 1, 'QA Engineer', '2025-05-14', 58000.00, 'ACTIVE'),
('Arjun Nair', 'arjun.nair@example.com', 4, 3, 'Delivery Manager', '2022-08-01', 98000.00, 'ACTIVE');

INSERT INTO attendance (employee_id, work_date, hours_worked) VALUES
(1, '2026-07-01', 8.00),
(2, '2026-07-01', 8.50),
(3, '2026-07-01', 7.50),
(4, '2026-07-01', 8.00),
(5, '2026-07-01', 9.00),
(6, '2026-07-01', 8.00);

-- Basic selection, filtering, ordering, and aliases.
SELECT
  employee_id AS id,
  employee_name AS name,
  designation,
  salary
FROM employees
WHERE status = 'ACTIVE' AND salary >= 60000
ORDER BY salary DESC;

-- Inner join across employees, departments, and projects.
SELECT
  e.employee_name,
  d.department_name,
  p.project_name,
  p.client_name
FROM employees e
JOIN departments d ON d.department_id = e.department_id
LEFT JOIN projects p ON p.project_id = e.project_id
ORDER BY d.department_name, e.employee_name;

-- Aggregate functions with GROUP BY and HAVING.
SELECT
  d.department_name,
  COUNT(e.employee_id) AS employee_count,
  ROUND(AVG(e.salary), 2) AS average_salary,
  MAX(e.salary) AS highest_salary
FROM departments d
LEFT JOIN employees e ON e.department_id = d.department_id
GROUP BY d.department_id, d.department_name
HAVING COUNT(e.employee_id) > 0
ORDER BY average_salary DESC;

-- Subquery for employees earning above the company average.
SELECT employee_name, designation, salary
FROM employees
WHERE salary > (SELECT AVG(salary) FROM employees)
ORDER BY salary DESC;

-- Date functions for tenure calculation.
SELECT
  employee_name,
  hire_date,
  TIMESTAMPDIFF(MONTH, hire_date, CURDATE()) AS months_completed
FROM employees
ORDER BY hire_date;

-- String functions and pattern search.
SELECT
  UPPER(employee_name) AS display_name,
  SUBSTRING_INDEX(email, '@', 1) AS login_name
FROM employees
WHERE email LIKE '%.%@example.com';

-- View for reusable project allocation reporting.
CREATE OR REPLACE VIEW vw_project_allocation AS
SELECT
  p.project_name,
  p.client_name,
  COUNT(e.employee_id) AS assigned_count,
  COALESCE(SUM(e.salary), 0) AS monthly_salary_cost
FROM projects p
LEFT JOIN employees e ON e.project_id = p.project_id
GROUP BY p.project_id, p.project_name, p.client_name;

SELECT * FROM vw_project_allocation ORDER BY monthly_salary_cost DESC;

DELIMITER //

CREATE PROCEDURE give_department_increment(
  IN p_department_name VARCHAR(60),
  IN p_increment_percent DECIMAL(5, 2)
)
BEGIN
  UPDATE employees e
  JOIN departments d ON d.department_id = e.department_id
  SET e.salary = ROUND(e.salary + (e.salary * p_increment_percent / 100), 2)
  WHERE d.department_name = p_department_name
    AND e.status = 'ACTIVE';
END//

CREATE FUNCTION annual_salary(p_employee_id INT)
RETURNS DECIMAL(12, 2)
DETERMINISTIC
BEGIN
  DECLARE v_salary DECIMAL(10, 2);

  SELECT salary INTO v_salary
  FROM employees
  WHERE employee_id = p_employee_id;

  RETURN COALESCE(v_salary, 0) * 12;
END//

CREATE TRIGGER trg_salary_audit
BEFORE UPDATE ON employees
FOR EACH ROW
BEGIN
  IF OLD.salary <> NEW.salary THEN
    INSERT INTO salary_audit (employee_id, old_salary, new_salary)
    VALUES (OLD.employee_id, OLD.salary, NEW.salary);
  END IF;
END//

DELIMITER ;

START TRANSACTION;
  UPDATE employees
  SET project_id = 1
  WHERE employee_name = 'Nisha Thomas';

  INSERT INTO attendance (employee_id, work_date, hours_worked)
  VALUES (5, '2026-07-02', 8.25);
COMMIT;

CALL give_department_increment('Engineering', 8.00);

SELECT
  employee_name,
  salary,
  annual_salary(employee_id) AS yearly_salary
FROM employees
WHERE department_id = (
  SELECT department_id
  FROM departments
  WHERE department_name = 'Engineering'
);

SELECT * FROM salary_audit ORDER BY changed_at DESC;
