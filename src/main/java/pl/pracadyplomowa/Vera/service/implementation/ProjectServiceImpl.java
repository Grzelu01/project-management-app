package pl.pracadyplomowa.Vera.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pracadyplomowa.Vera.dto.ProjectDto;
import pl.pracadyplomowa.Vera.entity.Project;
import pl.pracadyplomowa.Vera.repository.ProjectRepository;
import pl.pracadyplomowa.Vera.service.ProjectService;
import pl.pracadyplomowa.Vera.service.UserService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private final ProjectRepository projectRepository;
    @Autowired
    private final UserService userService;

    public ProjectServiceImpl(ProjectRepository projectRepository, UserService userService){
        this.projectRepository = projectRepository;
        this.userService = userService;
    }

    @Override
    public List<ProjectDto> getAllProjects() {
        List<Project> projects = projectRepository.getByUserId(userService.getCurrentUser().getId());
        return projects.stream()
                .map(ProjectDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public void saveProject(ProjectDto projectDto) {

        Project project = new Project();
        project.setProjectName(projectDto.getProjectName());
        project.setDescription(projectDto.getDescription());
        project.setBudget(projectDto.getBudget());
        project.setUser(userService.getCurrentUser());
        projectRepository.save(project);
    }

    @Override
    public Project getByProjectName(String projectName) {
        return projectRepository.getByProjectName(projectName);
    }


    @Override
    public Project getProjectById(Integer id) {

        Optional<Project> optional = projectRepository.findById(id);
        Project project = null;
        if(optional.isPresent()){
            project = optional.get();
        } else {
            throw new RuntimeException("Nie znaleziono projektu z id: " + id);
        }
        return project;

    }
    @Override
    public void deleteProjectById(Integer id) {

        this.projectRepository.deleteById(id);
    }

    @Override
    public void editProject(ProjectDto projectDto) {

        Project project = projectRepository.findById(projectDto.getId()).orElseThrow();
        project.setProjectName(projectDto.getProjectName());
        project.setDescription(projectDto.getDescription());
        project.setBudget(projectDto.getBudget());
        projectRepository.save(project);
    }

    @Override
    public Project getCurrentProject() {

        return null;
    }
}