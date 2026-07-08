const employees = [
  { id: 1, name: "Ananya Rao", role: "Frontend Engineer", department: "Engineering", project: "Client Portal", salary: 72000 },
  { id: 2, name: "Rahul Menon", role: "Business Analyst", department: "Delivery", project: "Claims Modernization", salary: 68000 },
  { id: 3, name: "Meera Iyer", role: "Finance Executive", department: "Finance", project: "Payroll Review", salary: 54000 },
  { id: 4, name: "Kabir Shah", role: "HR Partner", department: "HR", project: "Campus Hiring", salary: 61000 }
];

const tableBody = document.querySelector("#employeeRows");
const searchInput = document.querySelector("#searchInput");
const departmentFilter = document.querySelector("#departmentFilter");
const employeeForm = document.querySelector("#employeeForm");
const toastElement = document.querySelector("#appToast");
const toastMessage = document.querySelector("#toastMessage");
const toast = window.bootstrap && toastElement
  ? bootstrap.Toast.getOrCreateInstance(toastElement)
  : null;

function formatCurrency(value) {
  return new Intl.NumberFormat("en-IN", {
    style: "currency",
    currency: "INR",
    maximumFractionDigits: 0
  }).format(value);
}

function getVisibleEmployees() {
  const keyword = searchInput.value.trim().toLowerCase();
  const department = departmentFilter.value;

  return employees.filter((employee) => {
    const matchesKeyword = [employee.name, employee.role, employee.project]
      .some((field) => field.toLowerCase().includes(keyword));
    const matchesDepartment = department === "all" || employee.department === department;
    return matchesKeyword && matchesDepartment;
  });
}

function renderEmployees() {
  const rows = getVisibleEmployees().map((employee) => `
    <tr>
      <td class="fw-semibold">${employee.name}</td>
      <td>${employee.role}</td>
      <td><span class="badge rounded-pill badge-dept">${employee.department}</span></td>
      <td>${employee.project}</td>
      <td><span class="salary-pill">${formatCurrency(employee.salary)}</span></td>
      <td class="text-end">
        <button class="btn btn-sm btn-outline-danger" type="button" data-delete-id="${employee.id}">Remove</button>
      </td>
    </tr>
  `);

  tableBody.innerHTML = rows.join("") || `
    <tr>
      <td colspan="6" class="text-center text-secondary py-4">No employees match the current filters.</td>
    </tr>
  `;

  updateMetrics();
}

function updateMetrics() {
  const projectCount = new Set(employees.map((employee) => employee.project)).size;
  const totalSalary = employees.reduce((sum, employee) => sum + employee.salary, 0);
  const averageSalary = employees.length ? Math.round(totalSalary / employees.length) : 0;

  document.querySelector("#totalEmployees").textContent = employees.length;
  document.querySelector("#activeProjects").textContent = projectCount;
  document.querySelector("#avgSalary").textContent = formatCurrency(averageSalary);
}

function showToast(message) {
  toastMessage.textContent = message;
  if (toast) {
    toast.show();
  }
}

employeeForm.addEventListener("submit", (event) => {
  event.preventDefault();
  employeeForm.classList.add("was-validated");

  if (!employeeForm.checkValidity()) {
    return;
  }

  const employee = {
    id: crypto.randomUUID(),
    name: document.querySelector("#employeeName").value.trim(),
    role: document.querySelector("#employeeRole").value.trim(),
    department: document.querySelector("#employeeDepartment").value,
    project: document.querySelector("#employeeProject").value.trim(),
    salary: Number(document.querySelector("#employeeSalary").value)
  };

  employees.push(employee);
  employeeForm.reset();
  employeeForm.classList.remove("was-validated");
  renderEmployees();
  showToast(`${employee.name} added to the directory.`);
});

tableBody.addEventListener("click", (event) => {
  const button = event.target.closest("[data-delete-id]");
  if (!button) {
    return;
  }

  const index = employees.findIndex((employee) => String(employee.id) === button.dataset.deleteId);
  if (index >= 0) {
    const [removed] = employees.splice(index, 1);
    renderEmployees();
    showToast(`${removed.name} removed from the directory.`);
  }
});

searchInput.addEventListener("input", renderEmployees);
departmentFilter.addEventListener("change", renderEmployees);

renderEmployees();
