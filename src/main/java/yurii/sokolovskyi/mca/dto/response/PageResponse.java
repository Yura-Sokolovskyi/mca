package yurii.sokolovskyi.mca.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PageResponse<T> {
    private Integer totalPages;
    private Long totalElements;
    private List<T> data;
}
