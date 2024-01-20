package com.example.demofeign.provide;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class ProvideEntity {
    private final String providerId;
    private final Item item;

    @RequiredArgsConstructor
    @Data
    public static class Item{
        private final String itemId;
        private final Long price;

    }
}
