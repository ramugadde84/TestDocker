package test.docker.container.TestDocker.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import test.docker.container.TestDocker.entity.EmployeeEntity;
import test.docker.container.TestDocker.model.Employee;
import test.docker.container.TestDocker.service.EmployeeService;

import java.util.Map;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @GetMapping
    public Map<String, Employee> getAllLocationWiseHighestSalaryEmployees() {
        return employeeService.fetchHighestSalaryByLocation();
    }

    @PostMapping
    public Employee saveBook(@RequestBody Employee employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        BeanUtils.copyProperties(employee,employeeEntity);
        return employeeService.saveEmployee(employeeEntity);
    }

}
