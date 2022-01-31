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

        orderRepository.findById(id).ifPresent(order -> model.addAttribute("order", order));

        return "panel/order-details";
    }

    @PostMapping("/panel/order-management")
    public String changeStatus(@RequestParam OrderStatus status, @RequestParam Long orderId){


        orderRepository.findById(orderId).ifPresent(order -> {
            order.setOrderStatus(status);
            orderRepository.save(order);
        });

        return "redirect:order-details/" + orderId;

    }

}
