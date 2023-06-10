package pl.pracadyplomowa.Vera.service;

import pl.pracadyplomowa.Vera.dto.ProjectDto;
import pl.pracadyplomowa.Vera.entity.Project;
import java.util.List;

public interface ProjectService {

    List<ProjectDto> getAllProjects();
    void saveProject(ProjectDto projectDto);
    Project getByProjectName(String projectName);
    Project getProjectById(Integer id);
    void deleteProjectById(Integer id);
    void editProject(ProjectDto projectDto);
    Project getCurrentProject();
}
