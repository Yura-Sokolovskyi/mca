package yurii.sokolovskyi.mca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yurii.sokolovskyi.mca.dto.request.CategoryRequest;
import yurii.sokolovskyi.mca.dto.response.CategoryResponse;
import yurii.sokolovskyi.mca.dto.response.ProductResponse;
import yurii.sokolovskyi.mca.entity.Category;
import yurii.sokolovskyi.mca.repository.CategoryRepository;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private FileService fileService;

    @Autowired
    private ProductService productService;


    public void create(CategoryRequest request) throws IOException {
        categoryRepository.save(categoryRequestToProduct(null, request));
    }

    public void update(Long id, CategoryRequest request) throws IOException {
        categoryRepository.save(categoryRequestToProduct(findOneById(id), request));
    }

    private Category categoryRequestToProduct(Category category, CategoryRequest request) throws IOException {

        if (category == null) {
            category = new Category();
        }
        category.setName(request.getName());
        category.setColor(request.getColor());

        if (request.getData() != null && !request.getData().isEmpty()) {
            String path = fileService.saveFile(request.getData(), request.getFileName());
            category.setImage(path);
        }
        return category;
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

    public void delete(Long id) {
        List<ProductResponse> productResponses = productService.findByCategory(id);
        if (productResponses.size() == 0){
            Category category = findOneById(id);
            categoryRepository.delete(category);
        } else {
            throw new IllegalArgumentException("Category with id: " + id + " has references to products. Delete category with all products?");
        }

    }



}
