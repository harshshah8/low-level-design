package vendingmachine;

public class Product {
    private String code;
    private String name;
    private Integer price;

    public Product(String code, String name, Integer price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public Integer getPrice() {
        return price;
    }
}
