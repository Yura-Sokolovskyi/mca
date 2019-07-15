package yurii.sokolovskyi.mca.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yurii.sokolovskyi.mca.dto.request.UserRequest;
import yurii.sokolovskyi.mca.dto.response.LoggingResponse;
import yurii.sokolovskyi.mca.service.UserService;



@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    

    @PostMapping("/login")
    public LoggingResponse myFunc(@RequestBody UserRequest request){
        LoggingResponse loggingResponse = null;
        if(userService.checkUser(request)) {
            loggingResponse =new LoggingResponse("index");
        }
        return loggingResponse;
    }












}
