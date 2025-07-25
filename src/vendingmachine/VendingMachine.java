package vendingmachine;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class VendingMachine {
    private Inventory inventory;
    private Integer currentAmount;
    private String selectedProductCode;
    private VendingMachineState state;

    private Map<Integer, Integer> cashInventory;

    public VendingMachine() {
        inventory = new Inventory();
        currentAmount = 0;
        state = VendingMachineState.IDLE;
        selectedProductCode = null;
        init();
    }

    private void init() {
        initializeCashInventory();
        loadProducts();
    }

    private void initializeCashInventory() {
        cashInventory = new HashMap<>();
        cashInventory.put(1, 100);
        cashInventory.put(5, 50);
        cashInventory.put(10, 20);
        cashInventory.put(20, 10);
    }

    private void loadProducts() {
        inventory.addProduct(new Product("A1", "Coke", 60), 10);
        inventory.addProduct(new Product("A2", "Pepsi", 55), 8);
        inventory.addProduct(new Product("B1", "Chips", 40), 10);
        inventory.addProduct(new Product("B2", "Chocolate", 15), 20);
    }

    public void displayProducts() {
        inventory.displayProducts();
    }

    public void selectProduct(String code) {
        if (!state.equals(VendingMachineState.IDLE)) {
            System.out.println("can not select the product, the machine is in " + state + "state");
            return;
        }

        Product product = inventory.getProduct(code);

        if (Objects.isNull(product)) {
            System.out.println("invalid product code");
            return;
        }

        if (!inventory.isProductAvailable(code)) {
            System.out.println("Product is not available");
            return;
        }

        selectedProductCode = product.getCode();
        state = VendingMachineState.PRODUCT_SELECTED;
        System.out.println("The product is selected");
        System.out.println("Please insert $" + product.getPrice());
    }

    public void insertMoney(Integer amount) {
        if (state.equals(VendingMachineState.IDLE) || state.equals(VendingMachineState.DISPENSING)) {
            System.out.println("can not inset money in " + state + "state");
            return;
        }

        currentAmount = currentAmount + amount;
        state = VendingMachineState.MONEY_INSERTED;

        System.out.println("Inserted $ " + amount + " Total amount $ " + currentAmount);
        Product product = inventory.getProduct(selectedProductCode);

        if (currentAmount >= product.getPrice()) {
            dispenseProduct();
        } else {
            int remainingAmount = product.getPrice() - currentAmount;
            System.out.println("need $" + remainingAmount + " more");
        }
    }

    public void dispenseProduct() {
        state = VendingMachineState.DISPENSING;
        Product product = inventory.getProduct(selectedProductCode);

        int change = currentAmount - product.getPrice();
        System.out.println("Dispensing " + product.getName());
        inventory.decrementStock(product.getCode());

        System.out.println("thank you for purchase");
        reset();
    }

    private void reset() {
        currentAmount = 0;
        selectedProductCode = null;
        state = VendingMachineState.IDLE;
    }

    public void getStatus() {
        System.out.println("\n=== Machine Status ===");
        System.out.println("State: " + state);
        System.out.println("Current Amount: $" + currentAmount);
        System.out.println("Selected Product: " +
                (selectedProductCode != null ? selectedProductCode : "None"));
    }

    public void restockProduct(String code, int quantity) {
        Product product = inventory.getProduct(code);
        if (product != null) {
            inventory.addProduct(product, inventory.getStock(code) + quantity);
            System.out.println("Restocked " + product.getName() + " with " + quantity + " units");
        }
    }

}
