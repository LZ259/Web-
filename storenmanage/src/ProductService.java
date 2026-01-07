
import java.util.List;

public class ProductService {
    private ProductDAO productDAO = new ProductDAO();

    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    public boolean addProduct(Product product) {
        return productDAO.addProduct(product);
    }

    public boolean updateProduct(Product product) {
        return productDAO.updateProduct(product);
    }

    public boolean updateProductStatus(int productId, int status) {
        return productDAO.updateProductStatus(productId, status);
    }

    public boolean deleteProduct(int id) {
        return productDAO.deleteProduct(id);
    }

    public Product getProductById(int id) {
        return productDAO.getProductById(id);
    }
}
