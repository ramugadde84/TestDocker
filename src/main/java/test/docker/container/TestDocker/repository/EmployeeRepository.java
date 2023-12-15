package test.docker.container.TestDocker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.docker.container.TestDocker.entity.EmployeeEntity;
import test.docker.container.TestDocker.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {
}
