package pl.pracadyplomowa.Vera.repository;

import pl.pracadyplomowa.Vera.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

    Project getByProjectName(String projectName);
    List<Project> getByUserId(Integer id);
}
