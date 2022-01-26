package com.iq.ema.repository;
import java.util.List;
import com.iq.ema.dto.EmployeeProject;
import com.iq.ema.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository <Employee,Long> {

    @Query(nativeQuery = true,value = "select e.first_name as firstName ,e.last_name as lastName,count(pe.employee_id) as projectCount " +
            "from employees e left join project_employee pe on pe.employee_id=e.employee_id " +
            "group by e.first_name ,e.last_name " +
            "order by 3 DESC")
    public List<EmployeeProject> employeeProjects();

    public Employee findByEmailId(String value);
}
