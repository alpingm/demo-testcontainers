package com.demo.testcontainer.demotestcontainers;

import com.demo.testcontainer.demotestcontainers.model.Item;
import com.demo.testcontainer.demotestcontainers.model.ItemDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void createItem(ItemDto itemDto) {
        Item item = ItemMapper.toItem(itemDto);
        itemRepository.save(item);
    }

    public List<ItemDto> getItems() {
        return itemRepository.findAll().stream().map(ItemMapper::toDto).toList();
    }

    public ItemDto getItem(String id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
        return ItemMapper.toDto(item);
    }
}
