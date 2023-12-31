package pro.sky.mikhaillukichevcollections.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.mikhaillukichevcollections.model.Employee;
import pro.sky.mikhaillukichevcollections.service.DepartmentService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/min-salary")
    public Employee findLowestSalaryEmployeeInDepartment(@RequestParam(value = "departmentId") int departmentId) {
        return departmentService.findLowestSalaryEmployeeInDepartment(departmentId);
    }

    @GetMapping(path = "/max-salary")
    public Employee findHighestSalaryEmployeeInDepartment(@RequestParam(value = "departmentId") int departmentId) {
        return departmentService.findHighestSalaryEmployeeInDepartment(departmentId);
    }

    @GetMapping(value = "/all", params = {"departmentId"})
    public List<Employee> getEmployees(@RequestParam(value = "departmentId") int departmentId) {
        return departmentService.getEmployeesByDepartment(departmentId);
    }

    @GetMapping(value = "/all")
    public Map<Integer, List<Employee>> getEmployees() {
        return departmentService.getAllEmployees();
    }
}
