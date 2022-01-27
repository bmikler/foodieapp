package pl.javastart.foodieapp.entity;

import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;
    @ManyToMany
    @JoinTable(name = "order_item",
            joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "orderId"),
            inverseJoinColumns = @JoinColumn(name = "item_id", referencedColumnName = "itemId")
    )
    private List<Item> items = new ArrayList<>();
    @NotBlank
    private String address;
    @NotBlank
    private String telephone;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public Order() {
    }

    public Order(List<Item> items, String address, String telephone, OrderStatus orderStatus) {
        this.items = items;
        this.address = address;
        this.telephone = telephone;
        this.orderStatus = orderStatus;
    }

    public List<Item> getItems() {
        return items;
    }

    public String getAddress() {
        return address;
    }

    public String getTelephone() {
        return telephone;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void addItemToOrder(Item item) {
        items.add(item);
    }

    public void removeItemFromOrder(Item item){
        items.remove(item);
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
