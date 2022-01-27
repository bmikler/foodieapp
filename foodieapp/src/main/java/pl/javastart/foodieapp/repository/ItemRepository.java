package pl.javastart.foodieapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.javastart.foodieapp.entity.Item;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Optional<Item> findByName(String name);

    Optional<Item> findByItemId(long itemId);
}
