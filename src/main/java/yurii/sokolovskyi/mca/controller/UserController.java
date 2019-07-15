package yurii.sokolovskyi.mca.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yurii.sokolovskyi.mca.dto.request.UserRequest;
import yurii.sokolovskyi.mca.dto.response.CategoryResponse;
import yurii.sokolovskyi.mca.dto.response.LoggingResponse;
import yurii.sokolovskyi.mca.dto.response.UserSelectResponse;
import yurii.sokolovskyi.mca.service.UserService;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/select-all")
    public List<UserSelectResponse> getAllForSelect(){return userService.findAll();}

}
