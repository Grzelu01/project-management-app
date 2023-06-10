package pl.pracadyplomowa.Vera.repository;

import org.springframework.stereotype.Repository;
import pl.pracadyplomowa.Vera.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String name);
}
