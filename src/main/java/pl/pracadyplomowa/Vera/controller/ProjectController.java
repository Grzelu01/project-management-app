package pl.pracadyplomowa.Vera.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.pracadyplomowa.Vera.dto.ProjectDto;
import pl.pracadyplomowa.Vera.entity.Project;
import pl.pracadyplomowa.Vera.service.ProjectService;
import pl.pracadyplomowa.Vera.service.UserService;
import java.util.List;


@Controller
public class ProjectController {

    @Autowired
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService, UserService userService){
        this.projectService = projectService;
    }

    @GetMapping("/mainPage")
    public String showMainPage(){

        return "mainPage";
    }

    @GetMapping("/projects")
    public String showProjects (Model model) {

        List<ProjectDto> projectsList = projectService.getAllProjects();
        model.addAttribute("projects", projectsList);
        return "projects";
    }

    @GetMapping("/newProject")
    public String showNewProjectForm(Model model) {

        ProjectDto project = new ProjectDto();
        model.addAttribute("project", project);
        return "newProject";
    }

    @PostMapping("/saveProject")
    public String saveProject(@Valid @ModelAttribute("project") ProjectDto projectDto, BindingResult result, Model model) {

        //User currentUser = userService.getCurrentUser();
        Project existingProject = projectService.getByProjectName(projectDto.getProjectName());
        List<ProjectDto> getAllUserProjects = projectService.getAllProjects();

        if(/*getAllUserProjects.contains(existingProject.getProjectName()) && */existingProject != null && existingProject.getProjectName() != null && !existingProject.getProjectName().isEmpty()){
            result.rejectValue("projectName", null,
                    "Projekt o takiej nazwie już istnieje");
        }

        if(result.hasErrors()){
            model.addAttribute("project", projectDto);
            return "/newProject";
        }

        projectService.saveProject(projectDto);

        return "redirect:/newProject?success";
    }

    @GetMapping("/editProject/{id}")
    public String showEditForm(@PathVariable(value = "id") Integer id, Model model) {

        Project project = projectService.getProjectById(id);
        model.addAttribute("project", project);
        return "editProject";
    }

    @PostMapping("/editProject/{id}")
    public String editProject(@Valid @ModelAttribute("project") ProjectDto projectDto) {

        projectService.editProject(projectDto);
        return "redirect:/projects";
    }

    @GetMapping("/deleteProject/{id}")
    public String deleteProject(@PathVariable(value = "id") Integer id){

        this.projectService.deleteProjectById(id);
        return "redirect:/projects";
    }
}
