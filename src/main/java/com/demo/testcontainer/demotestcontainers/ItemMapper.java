package com.demo.testcontainer.demotestcontainers;

import com.demo.testcontainer.demotestcontainers.model.Item;
import com.demo.testcontainer.demotestcontainers.model.ItemDto;
import com.demo.testcontainer.demotestcontainers.model.ItemRequest;
import com.demo.testcontainer.demotestcontainers.model.ItemResponse;

public class ItemMapper {

    private ItemMapper() {}

    public static ItemResponse toResponse(ItemDto itemDto) {
        return new ItemResponse(itemDto.id(), itemDto.name());
    }

    public static ItemDto toDto(ItemRequest itemRequest) {
        return new ItemDto(itemRequest.id(), itemRequest.name());
    }

    public static ItemDto toDto(Item item) {
        return new ItemDto(item.getItemId(), item.getName());
    }

    public static Item toItem(ItemDto itemDto) {
        return new Item(itemDto.id(), itemDto.name());
    }
}
