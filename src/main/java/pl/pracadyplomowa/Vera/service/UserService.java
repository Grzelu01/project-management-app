package pl.pracadyplomowa.Vera.service;

import pl.pracadyplomowa.Vera.dto.UserDto;
import pl.pracadyplomowa.Vera.entity.User;
import java.util.List;

public interface UserService {

    void saveUser(UserDto userDto);
    User findUserByEmail(String email);
    User getCurrentUser();
    List<UserDto> findAllUsers();
}
