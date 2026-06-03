package VendingMachine.core;

import java.util.*;

public class ItemShelf {
    private final int code;
    private final List<Products> products;
    private boolean isSoldOut;

    public ItemShelf(int code) {
        this.code = code;
        this.products = new ArrayList<>();
        this.isSoldOut = true;
    }

    public void addProduct(Products product) {
        this.products.add(product);
        this.isSoldOut = false;
    }

    public Products dispenseProduct() {
        if (products.isEmpty()) {
            throw new IllegalStateException("No products available in the shelf.");
        }
        Products dispensedProduct = products.remove(0);
        if (products.isEmpty()) {
            this.isSoldOut = true;
        }
        return dispensedProduct;
    }

    public int getCode() {
        return code;
    }
    
    public boolean isSoldOut() {
        return isSoldOut;
    }

    public List<Products> getProducts() {
        return products;
    }
}
