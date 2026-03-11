package example.controller;


import example.dto.UserDto;
import example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public boolean login(@RequestBody UserDto userDto){
        boolean result = userService.login(userDto);
        return result;
    }
    @GetMapping
    public List<UserDto> findAll(){
        List<UserDto> userDtoList = userService.find();
        return userDtoList;
    }
    @GetMapping("/detail")
    public UserDto findDetail(@RequestParam int user_no){
        UserDto result = userService.findDetail(user_no);
        return result;
    }
    @DeleteMapping
    public boolean detete(@RequestParam int user_no){
        boolean result = userService.delete(user_no);
        return result;
    }

    @PutMapping
    public boolean update(@RequestBody UserDto userDto){
        boolean result = userService.update(userDto);
        return result;
    }

}