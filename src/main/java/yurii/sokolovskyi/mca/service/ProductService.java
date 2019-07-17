package yurii.sokolovskyi.mca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yurii.sokolovskyi.mca.dto.request.ProductRequest;
import yurii.sokolovskyi.mca.dto.response.ProductResponse;
import yurii.sokolovskyi.mca.entity.Category;
import yurii.sokolovskyi.mca.entity.IngredientCount;
import yurii.sokolovskyi.mca.entity.Product;
import yurii.sokolovskyi.mca.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private IngredientCountService ingredientCountService;


    public void create(ProductRequest request) {
        productRepository.save(productRequestToProduct(null, request));
    }

    public void update(Long id, ProductRequest request) {
        productRepository.save(productRequestToProduct(findOneById(id), request));
    }

    private Product productRequestToProduct(Product product, ProductRequest request) {
        if (product == null) {
            product = new Product();
            productRepository.save(product);
        }
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setImage(request.getImage());
        product.setInfo(request.getInfo());
        product.setCategory(categoryService.findOneById(request.getCategoryId()));
        for (Map.Entry<Long, Double> entry : request.getIngredientsCount().entrySet()) {
            Long aLong = entry.getKey();
            Double aDouble = entry.getValue();
            ingredientCountService.create(aDouble, aLong, product, null);
        }
        return product;
    }

    public List<ProductResponse> findByCategory(Long id){
        return productRepository.findByCategoryId(categoryService.findOneById(id))
                .stream().map(ProductResponse::new).collect(Collectors.toList());
    }

    public Product findOneById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product with id " + id + " not exists"));
    }

    public void deleteProductsByCategory(Long id) {
        productRepository.findByCategoryId(categoryService.findOneById(id))
                .forEach(product -> productRepository.delete(product));
        categoryService.delete(id);
    }
}
