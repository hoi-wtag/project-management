package com.iq.ema.repository;
import java.util.List;
import java.util.Optional;

import com.iq.ema.dto.EmployeeProject;
import com.iq.ema.model.Employee;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface EmployeeRepository extends JpaRepository <Employee,Long> {

    @Query(nativeQuery = true,value = "select e.first_name as firstName ,e.last_name as lastName,count(pe.employee_id) as projectCount " +
            "from employees e left join project_employee pe on pe.employee_id=e.employee_id " +
            "group by e.first_name ,e.last_name " +
            "order by 3 DESC")
    public List<EmployeeProject> employeeProjects();

    public Employee findByEmailId(String value);

    @EntityGraph(value = "employee.projects")
    Optional<Employee> findByEmployeeId(long id);

    @Query(value = "select * from employees e where e.first_name like %:value% or e.last_name like %:value% or e.email_id like %:value% ", nativeQuery = true)
    List<Employee> findByKeyword(@Param("value") String value);
}
