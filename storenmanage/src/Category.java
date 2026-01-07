
public class Category {
    private int id;
    private String name;
    private String description;
    private int status;
    private String createdAt;

    // 构造函数
    public Category() {}

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
        this.status = 1; // 默认启用
    }

    // Getter和Setter方法
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}
