package yurii.sokolovskyi.mca.dto.response;

import lombok.Getter;
import lombok.Setter;
import yurii.sokolovskyi.mca.entity.Category;

@Getter
@Setter
public class CategoryResponse {
    private String name;
    private Long id;

    public CategoryResponse(Category category) {
        name = category.getName();
        id = category.getId();
    }
}
