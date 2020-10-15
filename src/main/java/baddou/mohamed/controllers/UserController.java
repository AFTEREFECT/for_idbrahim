package baddou.mohamed.controllers;

import baddou.mohamed.requests.UserRequest;
import baddou.mohamed.responses.UserResponse;
import baddou.mohamed.services.UserService;
import baddou.mohamed.shared.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;


    @GetMapping()
    public String getUser() {
        return "get uset is here";
    }

    @PostMapping()
    public UserResponse createUser(@RequestBody UserRequest userRequest) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userRequest, userDto);

        UserDto createUser = userService.createUser(userDto);

        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(createUser, userResponse);


        return userResponse;


    }


    @PutMapping()
    public String puttUser() {
        return "put modifie user is here";
    }

    @DeleteMapping()
    public String deletetUser() {
        return "delete user is here";
    }


}
