package pl.javastart.foodieapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.javastart.foodieapp.entity.Order;
import pl.javastart.foodieapp.entity.OrderStatus;
import pl.javastart.foodieapp.repository.OrderRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class PanelController {

    private OrderRepository orderRepository;

    public PanelController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/panel/order-management")
    public String panelPage(@RequestParam(required = false) OrderStatus status, Model model) {

        List<Order> orders;

        if(status != null){
            orders = orderRepository.findByOrderStatus(status);
        } else {
            orders = orderRepository.findAll();
        }

        model.addAttribute("orders", orders);

        return "panel/order-management";
    }

    @GetMapping("panel/order-details/{id}")
    public String orderDetail(@PathVariable Long id, Model model){

        return orderRepository.findById(id)
                .map(order -> orderPage(order, model))
                .orElse("redirect:/panel/order-management");

    }

    @PostMapping("/panel/order-management")
    public String changeStatus(@RequestParam OrderStatus status, @RequestParam Long orderId, Model model){


        Optional<Order> order = orderRepository.findById(orderId);

        order.ifPresent(o -> {
            o.setOrderStatus(status);
            orderRepository.save(o);
        });

        return order
                .map(o -> orderPage(o, model))
                .orElse("redirect:/panel/order-management");

    }

    private String orderPage(Order order, Model model) {

        model.addAttribute("order", order);

        return "panel/order-details";

    }

}
