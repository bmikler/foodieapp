package pl.javastart.foodieapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.javastart.foodieapp.entity.Order;
import pl.javastart.foodieapp.entity.OrderStatus;
import pl.javastart.foodieapp.repository.ItemRepository;
import pl.javastart.foodieapp.repository.OrderRepository;
import pl.javastart.foodieapp.session.ClientOrder;

import javax.validation.ConstraintViolationException;

@Controller
public class OrderController {

    private ClientOrder clientOrder;
    private OrderRepository orderRepository;
    private ItemRepository itemRepository;

    public OrderController(ClientOrder clientOrder, OrderRepository orderRepository, ItemRepository itemRepository) {
        this.clientOrder = clientOrder;
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
    }

    @GetMapping("/order")
    public String orderPage(Model model){

        model.addAttribute("items", clientOrder.getAllItems());
        model.addAttribute("fullPrice", clientOrder.getOrderPrice());

        return "order";
    }

    @PostMapping("/add-to-order")
    public String addItemToOrder(@RequestParam long itemId) {

        itemRepository.findByItemId(itemId).ifPresent(item -> clientOrder.addItemToOrder(item));

        return "redirect:/";

    }

    @PostMapping("/create-order")
    public String createOrder(@RequestParam String address, @RequestParam String phone, Model model){

        Order order = clientOrder.getOrder();

        try {
            order.setAddress(address);
            order.setTelephone(phone);
            orderRepository.save(order);
            clientOrder.clear();

            model.addAttribute("success","Order is saved! We will deliver Your food as soon as possible!");
        } catch (ConstraintViolationException e) {
            model.addAttribute("error","Something goes wrong :( Please try again");
        }


        return "message";

    }


}
