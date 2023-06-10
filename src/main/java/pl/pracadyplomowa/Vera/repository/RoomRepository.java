package pl.pracadyplomowa.Vera.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pracadyplomowa.Vera.entity.Room;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

    List<Room> getAllByProjectId(Integer projectId);
}
