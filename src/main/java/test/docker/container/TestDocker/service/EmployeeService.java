package test.docker.container.TestDocker.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.docker.container.TestDocker.entity.EmployeeEntity;
import test.docker.container.TestDocker.model.Employee;
import test.docker.container.TestDocker.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public Employee saveEmployee(EmployeeEntity employee) {
        Employee savedEmployeeData = new Employee();

        EmployeeEntity employeeEntity = employeeRepository.save(employee);
        BeanUtils.copyProperties(employeeEntity,savedEmployeeData);
        return savedEmployeeData;
    }
    public List<Employee> formListEmployees() {
        List<Employee> employees = new ArrayList<>();
        Employee employee = getEmployee("Ramu","Mineapolis",10000);
        Employee employee1 = getEmployee("Rajesh","California",20000);
        Employee employee2 = getEmployee("Sathish","Dallas",10000);
        Employee employee3 = getEmployee("Divya","Dallas",20000);
        employees.add(employee);
        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
        return employees;
    }

    public Map<String,Employee> fetchHighestSalaryByLocation() {
        Map<String, Employee> highestSalaryByLocation = formListEmployees().stream()
                .collect(Collectors.groupingBy(Employee::getCity,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparing(Employee::getSalary)),
                                Optional -> Optional.orElse(null)
                        )));
        return highestSalaryByLocation;
    }

    @NotNull
    private static Employee getEmployee(String name,String city,Integer salary) {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setCity(city);
        employee.setSalary(salary);
        return employee;
    }
}
