package VendingMachine.core;

import VendingMachine.enums.ItemType;

public class Products {
    private final ItemType itemType;
    private final int price;

    public Products(ItemType itemType, int price) {
        this.itemType = itemType;
        this.price = price;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return itemType.name();
    }
}
