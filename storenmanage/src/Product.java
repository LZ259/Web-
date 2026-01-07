
public class Product {
    private int id;
    private String name;
    private int categoryId;
    private String description;
    private double price;
    private int stock;
    private int status; // 1:上架 0:下架
    private String createdAt;

    // 构造函数
    public Product() {}

    public Product(String name, int categoryId, String description, double price, int stock) {
        this.name = name;
        this.categoryId = categoryId;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.status = 1; // 默认上架
    }

    // Getter和Setter方法
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}
