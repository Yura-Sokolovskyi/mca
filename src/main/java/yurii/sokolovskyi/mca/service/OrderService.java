package yurii.sokolovskyi.mca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yurii.sokolovskyi.mca.dto.request.OrderRequest;
import yurii.sokolovskyi.mca.entity.Order;
import yurii.sokolovskyi.mca.entity.ProductCount;
import yurii.sokolovskyi.mca.entity.User;
import yurii.sokolovskyi.mca.repository.OrderRepository;
import yurii.sokolovskyi.mca.repository.ProductCountRepository;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductCountRepository productCountRepository;

    @Autowired
    private UserService userService;



    public void create(OrderRequest request) {
        Order order = new Order();
        User user = userService.findOne(request.getUserId());
        order.setFinished(true);
        order.setUser(user);
        orderRepository.save(order);
        List<ProductCount> productCounts = user.getCart().getProductCounts();
        productCounts.forEach(productCount -> productCount.setOrder(order));
        productCounts.forEach(productCount -> productCount.setCart(null));
        productCounts.forEach(productCount -> productCountRepository.save(productCount));

    }






}
