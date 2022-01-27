package pl.javastart.foodieapp.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pl.javastart.foodieapp.entity.Item;
import pl.javastart.foodieapp.entity.Order;
import pl.javastart.foodieapp.entity.OrderStatus;

import java.util.List;
import java.util.stream.Collectors;

@Component
@SessionScope
public class ClientOrder {

    private Order order;

    public ClientOrder() {
        clear();
    }

    public Order getOrder() {
        return order;
    }

    public void addItemToOrder(Item item) {
        order.addItemToOrder(item);
    }

    public List<Item> getAllItems(){
        return order.getItems();
    }

    public double getOrderPrice() {

        return order.getItems().stream()
                .map(Item::getPrice)
                .mapToDouble(Double::doubleValue)
                .sum();

    }

    public void clear() {

        this.order = new Order();
        order.setOrderStatus(OrderStatus.NEW);

    }

}
