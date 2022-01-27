package pl.javastart.foodieapp.repository;

import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.javastart.foodieapp.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
