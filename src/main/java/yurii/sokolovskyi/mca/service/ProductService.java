package yurii.sokolovskyi.mca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yurii.sokolovskyi.mca.dto.request.ProductRequest;
import yurii.sokolovskyi.mca.entity.Product;
import yurii.sokolovskyi.mca.repository.CategoryRepository;
import yurii.sokolovskyi.mca.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    public void create(ProductRequest request){

        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setImage(request.getImage());
        product.setInfo(request.getInfo());
        product.setCategory(categoryService.findOneById(request.getCategoryId()));
        productRepository.save(product);

    }

}
