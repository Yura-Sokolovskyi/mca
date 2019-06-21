package yurii.sokolovskyi.mca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yurii.sokolovskyi.mca.dto.request.CategoryRequest;
import yurii.sokolovskyi.mca.dto.response.CategoryResponse;
import yurii.sokolovskyi.mca.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public void create(@RequestBody CategoryRequest request) {
        categoryService.create(request);
    }

    @GetMapping
    public List<CategoryResponse> findAll() {
        return categoryService.findAll();
    }
}
