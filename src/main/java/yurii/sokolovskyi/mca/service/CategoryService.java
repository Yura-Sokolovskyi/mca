package yurii.sokolovskyi.mca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yurii.sokolovskyi.mca.dto.request.CategoryRequest;
import yurii.sokolovskyi.mca.dto.response.CategoryResponse;
import yurii.sokolovskyi.mca.entity.Category;
import yurii.sokolovskyi.mca.repository.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public void create(CategoryRequest request){
        Category category = new Category();
        category.setName(request.getName());
        categoryRepository.save(category);
    }

    public List<CategoryResponse> findAll() {
        return categoryRepository.findAll().stream()
                .map(CategoryResponse::new)
                .collect(Collectors.toList());
    }

    public Category findOneById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category with id " + id + " not exists"));
    }


}
