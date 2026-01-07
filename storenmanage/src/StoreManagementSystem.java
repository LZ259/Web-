
import java.util.List;
import java.util.Scanner;

public class StoreManagementSystem {
    private AdminService adminService = new AdminService();
    private CategoryService categoryService = new CategoryService();
    private ProductService productService = new ProductService();
    private Scanner scanner = new Scanner(System.in);
    private boolean isLoggedIn = false;

    public void start() {
        System.out.println("\n=== 网上商城后台管理系统 ===");
        while (true) {
            if (!isLoggedIn) {
                showLoginMenu();
            } else {
                showMainMenu();
            }
        }
    }

    private void showLoginMenu() {
    System.out.println("\n--- 登录菜单 ---");
    System.out.println("1. 管理员登录");
    System.out.println("2. 管理员注册");
    System.out.println("3. 退出系统");
    System.out.print("请选择操作: ");

    int choice = getIntInput();
    switch (choice) {
        case 1:
            login();
            break;
        case 2:
            register();
            break;
        case 3:
            System.out.println("感谢使用，再见！");
            System.exit(0);
            break;
        default:
            System.out.println("无效选择，请重新输入");
    }
}


    private void login() {
        System.out.print("请输入用户名: ");
        String username = scanner.nextLine();
        System.out.print("请输入密码: ");
        String password = scanner.nextLine();

        if (adminService.login(username, password)) {
            isLoggedIn = true;
            System.out.println("登录成功！欢迎回来，" + username);
        } else {
            System.out.println("登录失败！用户名或密码错误");
        }
    }

    private void showMainMenu() {
        System.out.println("\n--- 主菜单 ---");
        System.out.println("1. 商品分类管理");
        System.out.println("2. 商品管理");
        System.out.println("3. 退出登录");
        System.out.print("请选择操作: ");

        int choice = getIntInput();
        switch (choice) {
            case 1:
                categoryManagement();
                break;
            case 2:
                productManagement();
                break;
            case 3:
                logout();
                break;
            default:
                System.out.println("无效选择，请重新输入");
        }
    }

    private void categoryManagement() {
        while (true) {
            System.out.println("\n--- 商品分类管理 ---");
            System.out.println("1. 查看所有分类");
            System.out.println("2. 添加分类");
            System.out.println("3. 修改分类");
            System.out.println("4. 删除分类");
            System.out.println("5. 返回主菜单");
            System.out.print("请选择操作: ");

            int choice = getIntInput();
            switch (choice) {
                case 1:
                    listAllCategories();
                    break;
                case 2:
                    addCategory();
                    break;
                case 3:
                    updateCategory();
                    break;
                case 4:
                    deleteCategory();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("无效选择，请重新输入");
            }
        }
    }

    private void productManagement() {
        while (true) {
            System.out.println("\n--- 商品管理 ---");
            System.out.println("1. 查看所有商品");
            System.out.println("2. 添加商品");
            System.out.println("3. 修改商品");
            System.out.println("4. 商品上架/下架");
            System.out.println("5. 删除商品");
            System.out.println("6. 返回主菜单");
            System.out.print("请选择操作: ");

            int choice = getIntInput();
            switch (choice) {
                case 1:
                    listAllProducts();
                    break;
                case 2:
                    addProduct();
                    break;
                case 3:
                    updateProduct();
                    break;
                case 4:
                    toggleProductStatus();
                    break;
                case 5:
                    deleteProduct();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("无效选择，请重新输入");
            }
        }
    }

    private void listAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        if (categories.isEmpty()) {
            System.out.println("暂无分类信息");
            return;
        }

        System.out.println("\n--- 分类列表 ---");
        System.out.printf("%-5s %-20s %-30s %-10s %-20s\n", "ID", "名称", "描述", "状态", "创建时间");
        System.out.println("------------------------------------------------------------------------");
        for (Category category : categories) {
            String status = category.getStatus() == 1 ? "启用" : "禁用";
            System.out.printf("%-5d %-20s %-30s %-10s %-20s\n",
                            category.getId(),
                            category.getName(),
                            category.getDescription() != null ? category.getDescription() : "",
                            status,
                            category.getCreatedAt());
        }
    }

    private void addCategory() {
        System.out.print("请输入分类名称: ");
        String name = scanner.nextLine();
        if (name.trim().isEmpty()) {
            System.out.println("分类名称不能为空");
            return;
        }

        System.out.print("请输入分类描述(可选): ");
        String description = scanner.nextLine();

        Category category = new Category(name, description);
        if (categoryService.addCategory(category)) {
            System.out.println("分类添加成功");
        } else {
            System.out.println("分类添加失败");
        }
    }

    private void updateCategory() {
        System.out.print("请输入要修改的分类ID: ");
        int id = getIntInput();

        Category category = categoryService.getCategoryById(id);
        if (category == null) {
            System.out.println("分类不存在");
            return;
        }

        System.out.println("当前分类信息:");
        System.out.println("名称: " + category.getName());
        System.out.println("描述: " + (category.getDescription() != null ? category.getDescription() : ""));
        System.out.println("状态: " + (category.getStatus() == 1 ? "启用" : "禁用"));

        System.out.print("请输入新的分类名称(回车保持不变): ");
        String name = scanner.nextLine();
        if (!name.trim().isEmpty()) {
            category.setName(name);
        }

        System.out.print("请输入新的分类描述(回车保持不变): ");
        String description = scanner.nextLine();
        if (!description.trim().isEmpty()) {
            category.setDescription(description);
        }

        System.out.print("请输入状态(1:启用 0:禁用)(回车保持不变): ");
        String statusStr = scanner.nextLine();
        if (!statusStr.trim().isEmpty()) {
            try {
                int status = Integer.parseInt(statusStr);
                if (status == 0 || status == 1) {
                    category.setStatus(status);
                }
            } catch (NumberFormatException e) {
                System.out.println("状态值无效，保持原状态");
            }
        }

        if (categoryService.updateCategory(category)) {
            System.out.println("分类修改成功");
        } else {
            System.out.println("分类修改失败");
        }
    }

    private void deleteCategory() {
        System.out.print("请输入要删除的分类ID: ");
        int id = getIntInput();

        if (categoryService.deleteCategory(id)) {
            System.out.println("分类删除成功");
        } else {
            System.out.println("分类删除失败");
        }
    }

    private void listAllProducts() {
        List<Product> products = productService.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("暂无商品信息");
            return;
        }

        System.out.println("\n--- 商品列表 ---");
        System.out.printf("%-5s %-20s %-15s %-10s %-10s %-10s %-20s\n",
                         "ID", "名称", "分类ID", "价格", "库存", "状态", "创建时间");
        System.out.println("--------------------------------------------------------------------------------");
        for (Product product : products) {
            String status = product.getStatus() == 1 ? "上架" : "下架";
            System.out.printf("%-5d %-20s %-15d %-10.2f %-10d %-10s %-20s\n",
                            product.getId(),
                            product.getName(),
                            product.getCategoryId(),
                            product.getPrice(),
                            product.getStock(),
                            status,
                            product.getCreatedAt());
        }
    }

    private void addProduct() {
        System.out.print("请输入商品名称: ");
        String name = scanner.nextLine();
        if (name.trim().isEmpty()) {
            System.out.println("商品名称不能为空");
            return;
        }

        System.out.print("请输入分类ID: ");
        int categoryId = getIntInput();

        System.out.print("请输入商品描述: ");
        String description = scanner.nextLine();

        System.out.print("请输入商品价格: ");
        double price = getDoubleInput();

        System.out.print("请输入商品库存: ");
        int stock = getIntInput();

        Product product = new Product(name, categoryId, description, price, stock);
        if (productService.addProduct(product)) {
            System.out.println("商品添加成功");
        } else {
            System.out.println("商品添加失败");
        }
    }

    private void updateProduct() {
        System.out.print("请输入要修改的商品ID: ");
        int id = getIntInput();

        Product product = productService.getProductById(id);
        if (product == null) {
            System.out.println("商品不存在");
            return;
        }

        System.out.println("当前商品信息:");
        System.out.println("名称: " + product.getName());
        System.out.println("分类ID: " + product.getCategoryId());
        System.out.println("描述: " + (product.getDescription() != null ? product.getDescription() : ""));
        System.out.println("价格: " + product.getPrice());
        System.out.println("库存: " + product.getStock());

        System.out.print("请输入新的商品名称(回车保持不变): ");
        String name = scanner.nextLine();
        if (!name.trim().isEmpty()) {
            product.setName(name);
        }

        System.out.print("请输入新的分类ID(回车保持不变): ");
        String categoryIdStr = scanner.nextLine();
        if (!categoryIdStr.trim().isEmpty()) {
            try {
                int categoryId = Integer.parseInt(categoryIdStr);
                product.setCategoryId(categoryId);
            } catch (NumberFormatException e) {
                System.out.println("分类ID无效，保持原分类");
            }
        }

        System.out.print("请输入新的商品描述(回车保持不变): ");
        String description = scanner.nextLine();
        if (!description.trim().isEmpty()) {
            product.setDescription(description);
        }

        System.out.print("请输入新的商品价格(回车保持不变): ");
        String priceStr = scanner.nextLine();
        if (!priceStr.trim().isEmpty()) {
            try {
                double price = Double.parseDouble(priceStr);
                product.setPrice(price);
            } catch (NumberFormatException e) {
                System.out.println("价格无效，保持原价格");
            }
        }

        System.out.print("请输入新的商品库存(回车保持不变): ");
        String stockStr = scanner.nextLine();
        if (!stockStr.trim().isEmpty()) {
            try {
                int stock = Integer.parseInt(stockStr);
                product.setStock(stock);
            } catch (NumberFormatException e) {
                System.out.println("库存无效，保持原库存");
            }
        }

        if (productService.updateProduct(product)) {
            System.out.println("商品修改成功");
        } else {
            System.out.println("商品修改失败");
        }
    }

    private void toggleProductStatus() {
        System.out.print("请输入要操作的商品ID: ");
        int id = getIntInput();

        Product product = productService.getProductById(id);
        if (product == null) {
            System.out.println("商品不存在");
            return;
        }

        int newStatus = product.getStatus() == 1 ? 0 : 1;
        String statusText = newStatus == 1 ? "上架" : "下架";

        if (productService.updateProductStatus(id, newStatus)) {
            System.out.println("商品" + statusText + "成功");
        } else {
            System.out.println("商品" + statusText + "失败");
        }
    }

    private void deleteProduct() {
        System.out.print("请输入要删除的商品ID: ");
        int id = getIntInput();

        if (productService.deleteProduct(id)) {
            System.out.println("商品删除成功");
        } else {
            System.out.println("商品删除失败");
        }
    }

    private void logout() {
        isLoggedIn = false;
        System.out.println("已退出登录");
    }

    private int getIntInput() {
        while (true) {
            try {
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("请输入有效的数字: ");
            }
        }
    }

    private double getDoubleInput() {
        while (true) {
            try {
                String input = scanner.nextLine();
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.print("请输入有效的数字: ");
            }
        }
    }
    private void register() {
    System.out.println("\n--- 管理员注册 ---");
    System.out.print("请输入用户名: ");
    String username = scanner.nextLine();
    if (username.trim().isEmpty()) {
        System.out.println("用户名不能为空");
        return;
    }

    System.out.print("请输入密码: ");
    String password = scanner.nextLine();
    if (password.trim().isEmpty()) {
        System.out.println("密码不能为空");
        return;
    }

    System.out.print("请输入邮箱: ");
    String email = scanner.nextLine();
    if (email.trim().isEmpty()) {
        System.out.println("邮箱不能为空");
        return;
    }

    Admin admin = new Admin(username, password, email);
    if (adminService.register(admin)) {
        System.out.println("注册成功！您可以使用新账户登录系统了。");
    } else {
        System.out.println("注册失败，请稍后重试。");
    }
}

}

