package models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Item {
    private String name;
    private String description;
    private String price;

    public Item(String name, String description, String price){
        this.name = name;
        this.description = description;
        this.price = price;
    }

}
