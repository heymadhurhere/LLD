package VendingMachine.core;

public class Inventory {
    private final ItemShelf[] shelves;

    public Inventory (int slotCount) {
        this.shelves = new ItemShelf[slotCount];
        initializeEmptyShelves();
    }

    private void initializeEmptyShelves() {
        int startCode = 101;
        for (int i = 0; i < shelves.length; i++) {
            shelves[i] = new ItemShelf(startCode);
            startCode++;
        }
    }

    public void addItemToShelf(Products product, int code) {
        for (ItemShelf shelf : shelves) {
            if (shelf.getCode() == code) {
                shelf.addProduct(product);
                return;
            }
        }
        throw new IllegalArgumentException("Invalid shelf code: " + code);
    }

    public Products getItem(int code) {
        for (ItemShelf shelf : shelves) {
            if (shelf.getCode() == code) {
                if (shelf.isSoldOut()) {
                    throw new IllegalStateException("Shelf " + code + " is sold out.");
                }
                return shelf.getProducts().get(0);
            }
        }
        throw new IllegalArgumentException("Invalid shelf code: " + code);
    }

    public void removeDispensedItem(int code) {
        for (ItemShelf shelf : shelves) {
            if (shelf.getCode() == code) {
                if (shelf.isSoldOut()) {
                    throw new IllegalStateException("Shelf " + code + " is sold out.");
                }
                shelf.dispenseProduct();
                return;
            }
        }
        throw new IllegalArgumentException("Invalid shelf code: " + code);
    }

    public boolean hasAnyItem() {
        for (ItemShelf shelf : shelves) {
            if (!shelf.isSoldOut()) {
                return true;
            }
        }
        return false;
    }
}
