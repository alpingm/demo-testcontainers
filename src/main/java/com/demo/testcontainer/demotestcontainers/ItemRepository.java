package com.demo.testcontainer.demotestcontainers;

import com.demo.testcontainer.demotestcontainers.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, String> {
}
