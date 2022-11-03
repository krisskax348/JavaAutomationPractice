package com.endava.utils;

import com.endava.models.Item;

import java.util.Comparator;

public class ItemNameComparator implements Comparator<Item> {
    @Override
    public int compare(Item item1, Item item2) {
        return item1.getName().compareTo(item2.getName());
        //return item2.getName().compareTo(item1.getName());
    }
}
