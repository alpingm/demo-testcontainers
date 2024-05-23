package com.demo.testcontainer.demotestcontainers;


import com.demo.testcontainer.demotestcontainers.model.ItemDto;
import com.demo.testcontainer.demotestcontainers.model.ItemRequest;
import com.demo.testcontainer.demotestcontainers.model.ItemResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<List<ItemResponse>> getItems() {
        List<ItemResponse> response = itemService.getItems()
                .stream()
                .map(ItemMapper::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemResponse> getItem(@PathVariable("id") String id) {
        ItemDto itemDto = itemService.getItem(id);
        ItemResponse response = ItemMapper.toResponse(itemDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/item")
    public ResponseEntity<ItemResponse> createItem(@RequestBody ItemRequest itemRequest) {
        ItemDto itemDto = ItemMapper.toDto(itemRequest);
        itemService.createItem(itemDto);
        return ResponseEntity.ok().build();
    }

}
