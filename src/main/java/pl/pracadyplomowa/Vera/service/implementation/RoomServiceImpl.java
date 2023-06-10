package pl.pracadyplomowa.Vera.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pracadyplomowa.Vera.dto.RoomDto;
import pl.pracadyplomowa.Vera.entity.Project;
import pl.pracadyplomowa.Vera.entity.Room;
import pl.pracadyplomowa.Vera.repository.ProjectRepository;
import pl.pracadyplomowa.Vera.repository.RoomRepository;
import pl.pracadyplomowa.Vera.service.RoomService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private final RoomRepository roomRepository;

    @Autowired
    private final ProjectRepository projectRepository;

    public RoomServiceImpl(RoomRepository roomRepository,
                           ProjectRepository projectRepository){
        this.roomRepository = roomRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public List<RoomDto> getAllRoomsByProjectId(Integer projectId) {
        List<Room> rooms = roomRepository.getAllByProjectId(projectId);
        return rooms.stream()
                .map(RoomDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public void saveRoom(RoomDto roomDto, Integer projectId) {
        Optional<Project> project = projectRepository.findById(projectId);
        if (project.isPresent()) {
            Room room = new Room();
            room.setType(roomDto.getType());
            room.setName(roomDto.getName());
            room.setTask(roomDto.getTask());
            room.setProject(project.get());
            roomRepository.save(room);
        }
    }

    @Override
    public Room getByRoomId(Integer id) {

        Optional<Room> optional = roomRepository.findById(id);
        Room room = null;
        if(optional.isPresent()){
            room = optional.get();
        } else {
            throw new RuntimeException("Nie znaleziono pomieszczenia z id: " + id);
        }
        return room;
    }

    @Override
    public void editRoom(RoomDto roomDto) {

        Room room = roomRepository.findById(roomDto.getId()).orElseThrow();
        room.setType(roomDto.getType());
        room.setName(roomDto.getName());
        room.setTask(roomDto.getTask());

        roomRepository.save(room);
    }

    @Override
    public void deleteRoom(Integer id) {

        this.roomRepository.deleteById(id);
    }
}
