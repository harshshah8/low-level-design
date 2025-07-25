package vendingmachine;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Inventory {
    private Map<String, Product> products;
    private Map<String, Integer> stock;

    private static final Logger log = Logger.getLogger(Inventory.class.getName());

    public Inventory() {
        products = new HashMap<>();
        stock = new HashMap<>();
    }

    public void addProduct(Product product, Integer quantity) {
        products.put(product.getCode(), product);
        stock.put(product.getCode(), quantity);
    }

    public Product getProduct(String code) {
        if (products.containsKey(code)) {
            return products.get(code);
        }
        log.info(String.format("Product with Code:%s is not available", code));
        return null;
    }

    public boolean isProductAvailable(String code) {
        return stock.getOrDefault(code, 0) > 0;
    }

    public void decrementStock(String code) {
        if (isProductAvailable(code)) {
            stock.put(code, stock.get(code) - 1);
        }
    }

    public int getStock(String code) {
        return stock.getOrDefault(code,0);
    }

    public void displayProducts() {
        System.out.println("------AVAILABLE PRODUCTS------");
        for(Product product: products.values()) {
            int quantity = stock.get(product.getCode());
            String status = quantity > 0 ? "Available (" + quantity + ")" : "Out of Stock";
            System.out.println(product.getName() + "-" + product.getCode() + "------" + status);
        }
    }
}
