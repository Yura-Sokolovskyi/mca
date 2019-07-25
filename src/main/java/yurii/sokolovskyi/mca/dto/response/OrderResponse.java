package yurii.sokolovskyi.mca.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class OrderResponse {
   private Long id;
   private String date;
   private String client;
   private Double price;
   private Boolean status;
}
