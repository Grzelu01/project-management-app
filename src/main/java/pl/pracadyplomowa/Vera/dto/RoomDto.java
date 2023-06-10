package pl.pracadyplomowa.Vera.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.pracadyplomowa.Vera.entity.Room;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String type;
    @NotEmpty(message = "Nazwa pomieszczenia nie może być pusta")
    private String name;
    private String task;


    public static RoomDto from(Room room){

        RoomDto roomDto = new RoomDto();
        roomDto.setId(room.getId());
        roomDto.setType(room.getType());
        roomDto.setName(room.getName());
        roomDto.setTask(room.getTask());
        return roomDto;
    }
}
