package pl.pracadyplomowa.Vera.repository;

import org.springframework.stereotype.Repository;
import pl.pracadyplomowa.Vera.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);
}

