package yurii.sokolovskyi.mca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import yurii.sokolovskyi.mca.entity.Category;
import yurii.sokolovskyi.mca.repository.CategoryRepository;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class MyRestorauntAdminApplication {

    @Autowired
    private CategoryRepository categoryRepository;

    public static void main(String[] args) {
        SpringApplication.run(MyRestorauntAdminApplication.class, args);
    }

}
