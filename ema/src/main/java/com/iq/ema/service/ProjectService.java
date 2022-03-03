package com.iq.ema.service;

import com.iq.ema.dto.ChartData;
import com.iq.ema.dto.ProjectDTO;
import com.iq.ema.dto.ProjectListDTO;
import com.iq.ema.exceptions.ResourceNotFoundException;
import com.iq.ema.model.Employee;
import com.iq.ema.model.Project;
import com.iq.ema.repository.ProjectRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    @Autowired
    ProjectRepository proRepo;

    public List<Project> getAll() {
        return proRepo.findAll();
    }

    public Project save(@Valid Project project) {

        return proRepo.save(project);
    }

    public Page<Project> findProjectWithPagination(int pageNumber, int pageSize){
        Page<Project> projects = proRepo.findAll(PageRequest.of(pageNumber, pageSize).withSort(Sort.Direction.ASC,"projectId"));
        return  projects;
    }
    public Project update(long id,Project project) {
        Project projectobj= proRepo.findByProjectId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not exist with id:"+id));
        projectobj.setName(project.getName());
        projectobj.setDescription(project.getDescription());
        projectobj.setStage(project.getStage());
        projectobj.setEmployees(project.getEmployees());
        return proRepo.save(projectobj);
    }
    public Optional<Project> findByProjectId(long id) {
        return proRepo.findByProjectId(id);
    }

    public void delete(Project project) {
        proRepo.delete(project);
    }

    public List<ChartData> getProjectStatusData() {
        return proRepo.getProjectStatus();
    }

}