package pl.pracadyplomowa.Vera.service;

import pl.pracadyplomowa.Vera.dto.RoomDto;
import pl.pracadyplomowa.Vera.entity.Room;
import java.util.List;

public interface RoomService {

    List<RoomDto> getAllRoomsByProjectId(Integer projectId);
    void saveRoom(RoomDto roomDto, Integer project);
    Room getByRoomId(Integer id);
    void editRoom(RoomDto roomDto);
    void deleteRoom (Integer id);
}
