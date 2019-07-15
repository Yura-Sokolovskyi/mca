package yurii.sokolovskyi.mca.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import yurii.sokolovskyi.mca.dto.request.PaginationRequest;
import yurii.sokolovskyi.mca.dto.request.ProductRequest;
import yurii.sokolovskyi.mca.dto.request.UserCriteria;
import yurii.sokolovskyi.mca.dto.request.UserRequest;
import yurii.sokolovskyi.mca.dto.response.*;
import yurii.sokolovskyi.mca.service.UserService;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/get-all")
    public List<UserSelectResponse> getAllForSelect(){return userService.findAll();}

    @GetMapping("/get-user-for-select")
    public PageResponse<UserSelectResponse> findPage(@Valid PaginationRequest paginationRequest) {
        return userService.findPage(paginationRequest);
    }

    @PostMapping("/findByFilter")
    public List<UserResponse> findByFilter (@Valid @RequestBody UserCriteria userCriteria){
        return userService.findByCriteria(userCriteria);
    }
}
