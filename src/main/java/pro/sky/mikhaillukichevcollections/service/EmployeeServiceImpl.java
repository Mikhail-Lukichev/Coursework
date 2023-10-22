package pro.sky.mikhaillukichevcollections.service;

import pro.sky.mikhaillukichevcollections.exception.EmployeeAlreadyAddedException;
import pro.sky.mikhaillukichevcollections.exception.EmployeeNotFoundException;
import org.springframework.stereotype.Service;
import pro.sky.mikhaillukichevcollections.exception.EmployeeStorageIsFullException;
import pro.sky.mikhaillukichevcollections.model.Employee;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final static int EMPLOYEE_MAX_COUNT = 20;

    Map<String, Employee> employees = new HashMap<String, Employee>();

    public int getEmployeeMaxCount() {
        return EMPLOYEE_MAX_COUNT;
    }

    public Map<String, Employee> getEmployees() {
        return employees;
    }

    public List<Employee> getEmployeesByDepartment(int department) {
        return employees.values().stream()
                .filter(employee -> employee.getDepartment() == department)
                .collect(Collectors.toList());
    }

    public Employee addEmployee(String firstName, String lastName, int department, int salary) {
        if (employees.size() >= EMPLOYEE_MAX_COUNT) {
            throw new EmployeeStorageIsFullException("Exception: adding employee " + firstName + " " + lastName + ". Cannot add employee. Storage is full");
        }
        if (employees.containsKey(firstName + lastName)) {
            throw new EmployeeAlreadyAddedException("Exception: adding employee " + firstName + " " + lastName + ". Such employee has been already added");
        }
        Employee employee = new Employee(firstName, lastName, department, salary);
        employees.put(firstName + lastName, employee);
        return employee;
    }

    public Employee removeEmployee(String firstName, String lastName) {
        if (employees.containsKey(firstName + lastName)) {
            Employee returnEmployee = employees.get(firstName + lastName);
            employees.remove(firstName + lastName);
            return returnEmployee;
        } else {
            throw new EmployeeNotFoundException("Exception: removing employee " + firstName + " " + lastName + ". Employee not found");
        }
    }

    public Employee findEmployee(String firstName, String lastName) {
        if (employees.containsKey(firstName + lastName)) {
            return employees.get(firstName + lastName);
        } else {
            throw new EmployeeNotFoundException("Exception: finding employee " + firstName + " " + lastName + ". Employee not found");
        }
    }

    public void addTestEmployees() {
        this.addEmployee("John", "Tainsh", 1, 29000);
        this.addEmployee("Sarah", "Fetch", 1, 35000);
        this.addEmployee("Michael", "Farnwell", 1, 28000);

        this.addEmployee("David", "Jones", 2, 22000);
        this.addEmployee("Tatiana", "Barker", 2, 27000);
        this.addEmployee("Nicola", "Gilmore", 2, 23000);
    }
}
