package pl.javastart.foodieapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.javastart.foodieapp.entity.Order;
import pl.javastart.foodieapp.entity.OrderStatus;

import java.util.List;
import java.util.stream.Collectors;


public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByOrderStatus(OrderStatus status);


}
