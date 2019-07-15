package yurii.sokolovskyi.mca.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("menu.html");
        registry.addViewController("/menu").setViewName("menu.html");
        registry.addViewController("/admin").setViewName("admin.html");
    }

}