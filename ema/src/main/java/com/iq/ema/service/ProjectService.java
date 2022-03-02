package com.iq.ema.service;

import com.iq.ema.dto.ChartData;
import com.iq.ema.dto.ProjectDTO;
import com.iq.ema.model.Project;
import com.iq.ema.repository.ProjectRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    @Autowired
    ProjectRepository proRepo;

    @Autowired
    private ModelMapper modelMapper;

    public Iterable<ProjectDTO> getAll() {

        return proRepo.findAll().stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
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

    private ProjectDTO convertEntityToDto(Project project){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO = modelMapper.map(project, ProjectDTO.class);
        return projectDTO;
    }
}