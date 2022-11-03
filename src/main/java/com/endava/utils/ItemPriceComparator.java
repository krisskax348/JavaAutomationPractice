package com.endava.utils;

import com.endava.models.Item;

import java.util.Comparator;

public class ItemPriceComparator implements Comparator<Item> {

    @Override
    public int compare(Item item1, Item item2) {
        //return item1.getPrice().compareTo(item2.getPrice());
        return item2.getPrice().compareTo(item1.getPrice());
    }
}
