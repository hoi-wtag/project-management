package com.iq.ema.repository;

import com.iq.ema.model.Employee;
import com.iq.ema.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project,Long> {
}
