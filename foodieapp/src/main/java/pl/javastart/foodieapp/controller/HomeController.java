package pl.javastart.foodieapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.javastart.foodieapp.entity.Item;
import pl.javastart.foodieapp.repository.ItemRepository;

import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    ItemRepository itemRepository;

    public HomeController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping("/")
    public String index(Model model){

        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);

        return "index";
    }


    @GetMapping("/item-page/{name}")
    public String getItem(@PathVariable String name, Model model){

        itemRepository.findByName(name).ifPresent(item -> model.addAttribute("item", item));

        return "item-page";

    }



}
