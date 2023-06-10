package pl.pracadyplomowa.Vera.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.pracadyplomowa.Vera.entity.Project;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDto {

    private Integer id;
    @NotEmpty(message = "Nazwa projektu nie może być pusta")
    private String projectName;
    private String description;
    private double budget;

    public static ProjectDto from(Project project) {

        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(project.getId());
        projectDto.setProjectName(project.getProjectName());
        projectDto.setDescription(project.getDescription());
        projectDto.setBudget(project.getBudget());
        return projectDto;
    }
}
