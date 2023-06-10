package pl.pracadyplomowa.Vera.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.pracadyplomowa.Vera.dto.RoomDto;
import pl.pracadyplomowa.Vera.entity.Room;
import pl.pracadyplomowa.Vera.service.RoomService;
import java.util.List;

@Controller
public class RoomController {

    @Autowired
    private final RoomService roomService;

    public RoomController(RoomService roomService){
        this.roomService = roomService;
    }

    @GetMapping("/{projectId}/rooms")
    public String showRooms(@PathVariable Integer projectId, Model model){

        List<RoomDto> roomsList = roomService.getAllRoomsByProjectId(projectId);
        System.out.println(roomsList);
        model.addAttribute("rooms", roomsList);
        return "rooms";
    }

    @GetMapping("/{projectId}/newRoom")
    public String showNewRoomForm(@PathVariable Integer projectId, Model model){

        RoomDto roomDto = new RoomDto();
        model.addAttribute("room", roomDto);
        model.addAttribute("projectId", projectId);
        return "newRoom";
    }

    @PostMapping("/{projectId}/saveRoom")
    public String saveRoom(@PathVariable Integer projectId, @Valid @ModelAttribute("room") RoomDto roomDto){

        roomService.saveRoom(roomDto, projectId);
        return "redirect:/{projectId}/newRoom?success";
    }

    @GetMapping ("/editRoom/{id}")
    public String showEditForm(@PathVariable(value = "id") Integer id, Model model){

        Room room = roomService.getByRoomId(id);
        model.addAttribute("room", room);
        return "editRoom";
    }

    @PostMapping("/editRoom/{id}")
    public String editRoom(@Valid @ModelAttribute("room") RoomDto roomDto){

        roomService.editRoom(roomDto);
        return "redirect:/projects";
    }

    @GetMapping("/deleteRoom/{id}")
    public String deleteRoom(@PathVariable(value = "id") Integer id){

        this.roomService.deleteRoom(id);
        return "redirect:/projects";
    }
}
