package com.iq.ema.repository;

import com.iq.ema.dto.ChartData;
import com.iq.ema.model.Employee;
import com.iq.ema.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project,Long> {

    @Query(nativeQuery = true,value = "select stage as label,count(*) as value " +
            "from projects " +
            "group by stage")
    public List<ChartData> getProjectStatus();
}
