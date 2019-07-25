package yurii.sokolovskyi.mca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yurii.sokolovskyi.mca.dto.request.OrderCriteria;
import yurii.sokolovskyi.mca.dto.request.OrderRequest;
import yurii.sokolovskyi.mca.dto.response.OrderResponse;
import yurii.sokolovskyi.mca.entity.Order;
import yurii.sokolovskyi.mca.entity.ProductCount;
import yurii.sokolovskyi.mca.entity.User;
import yurii.sokolovskyi.mca.repository.OrderRepository;
import yurii.sokolovskyi.mca.repository.ProductCountRepository;
import yurii.sokolovskyi.mca.repository.UserRepository;
import yurii.sokolovskyi.mca.specification.OrderSpecification;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductCountRepository productCountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DiscountService discountService;

    public void create(OrderRequest request) {
        Order order = new Order();
        User user = userRepository.getOne(request.getUserId());
        Double discount = discountService.getUserDiscount(request.getUserId());
        order.setFinished(true);
        order.setUser(user);
        orderRepository.save(order);
        List<ProductCount> productCounts = user.getCart().getProductCounts();
        productCounts.forEach(pc -> pc.setOrder(order));
        productCounts.forEach(pc -> pc.setCart(null));
        if(discount>0){
            productCounts.forEach(pc -> pc.setPrice(pc.getProduct().getPrice() - pc.getProduct().getPrice() * discount));
        } else {
            productCounts.forEach(pc -> pc.setPrice(pc.getProduct().getPrice()));
        }
        productCounts.forEach(pc -> productCountRepository.save(pc));
    }

    public void delete(Long id){
        productCountRepository.findProductCountByOrderId(id)
                .forEach(productCount -> productCountRepository.delete(productCount));
        orderRepository.deleteById(id);
    }

    public List<OrderResponse> findByCriteria(OrderCriteria criteria){
        return orderRepository.findAll(new OrderSpecification(criteria),criteria.getPaginationRequest()
                .toPageable()).stream().map(order -> new OrderResponse( order.getId(),dateFormater(order)
              ,
              order.getUser().getName(),new BigDecimal(order.getProductCounts().stream().map(ProductCount::getPrice)
                .reduce(0.0, Double::sum)).setScale(2, RoundingMode.HALF_UP).doubleValue()
              ,
              order.getFinished())).collect(Collectors.toList());
    }

    private String dateFormater (Order order){
        String date = "";
      LocalDateTime dateTime =order.getProductCounts().stream()
              .max(Comparator.comparing(ProductCount::getUpdateDateTime))
              .map(ProductCount::getUpdateDateTime).orElse(null);
      if (dateTime != null){
          date = dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
      }
      return date;
    }





}
