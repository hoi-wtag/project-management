package com.iq.ema.service;

import com.iq.ema.dto.ChartData;
import com.iq.ema.dto.EmployeeProject;
import com.iq.ema.model.Project;
import com.iq.ema.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProjectService {
    @Autowired
    ProjectRepository proRepo;

    public Iterable<Project> getAll() {

        return proRepo.findAll();
    }

    public Project save(Project project) {

        return proRepo.save(project);
    }

    public Optional<Project> findByProjectId(long id) {
        return proRepo.findById(id);
    }


    public void delete(Project project) {
        proRepo.delete(project);

    }

    public List<ChartData> getProjectStatusData() {
        return proRepo.getProjectStatus();
    }

}