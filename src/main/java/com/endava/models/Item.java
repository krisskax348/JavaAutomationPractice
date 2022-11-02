package models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Item {
    private String name;
    private String description;
    private Double price;

    public Item(String name, String description, Double price){
        this.name = name;
        this.description = description;
        this.price = price;
    }

}
